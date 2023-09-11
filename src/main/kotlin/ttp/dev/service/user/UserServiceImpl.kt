package ttp.dev.service.user

import io.ktor.server.plugins.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import ttp.dev.config.DeleteUserErrorCodes.DELETE_ERR
import ttp.dev.config.DeleteUserErrorCodes.INVALID_USER
import ttp.dev.config.DeleteUserErrorCodes.SUCCESS
import ttp.dev.data.model.Params.UpdateUserParams
import ttp.dev.data.model.UserInfo
import ttp.dev.data.table.mst_gender
import ttp.dev.data.table.mst_role
import ttp.dev.data.table.mst_user
import ttp.dev.extensions.toUserInfo
import ttp.dev.extensions.toUserInfoTotal
import ttp.dev.repository.DatabaseFactory.dbQuery
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UserServiceImpl : UserService {
    override suspend fun getUserByID(userID: String): UserInfo = dbQuery {
        mst_user.select { mst_user.userId eq userID }
            .singleOrNull()
            ?.toUserInfo() ?: throw NotFoundException("User not found")
    }

    override suspend fun updateUser(user: UpdateUserParams): Int = dbQuery {
        mst_user.update({ mst_user.userId eq user.userId }) {
            it[password] = user.password
            it[familyName] = user.familyName
            it[firstName] = user.firstName
            it[genderId] = user.genderId
            it[age] = user.age
            it[authorityId] = user.authorityId
            it[admin] = user.admin
            it[updateUserId] = user.updateUserId
            it[updateDate] = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")).toLong()
        }
    }

    override suspend fun deleteUser(userId: String, loggedInUserId: String): Int = dbQuery {
        if (userId == loggedInUserId) {
            INVALID_USER
        } else {
            val rowsDeleted = mst_user.deleteWhere { mst_user.userId eq userId }
            if (rowsDeleted > 0) {
                SUCCESS
            } else {
                DELETE_ERR
            }
        }
    }

    override suspend fun isUserExists(userId: String): Boolean = dbQuery {
        mst_user.select { mst_user.userId eq userId }.empty().not()
    }

    override suspend fun getStatistics(): Map<String, Int> = dbQuery {
        val statistics = mutableMapOf<String, Int>()

        val sql =
            """
            SELECT COALESCE(a.authority_name,'Chưa đăng kí') AS authority_name,
                COALESCE(u.male_count,0)AS male_count,
                COALESCE(u.female_count,0) AS female_count,
                COALESCE(u.age_0_19_count,0) AS age_0_19_count,
                COALESCE(u.age_20_plus_count,0) AS age_20_plus_count,
                COALESCE(u.age_unknown_count,0) AS age_unknown_count
            FROM (SELECT authority_id,
                COUNT(CASE WHEN gender_id = 2 THEN 1 END) AS male_count,
                COUNT(CASE WHEN gender_id = 1 THEN 1 END) AS female_count,
                COUNT(CASE WHEN age >= 0 AND age <= 19 THEN 1 END) AS age_0_19_count,
                COUNT(CASE WHEN age > 20 THEN 1 END) AS age_20_plus_count,
                COUNT(CASE WHEN age IS NULL THEN 1 END) AS age_unknown_count
            FROM mst_user 
            GROUP BY  authority_id) u
            FULL JOIN mst_role a ON u.authority_id = a.authority_id
          	ORDER BY a.authority_name ASC
            """.trimIndent()

        transaction {
            val connection = TransactionManager.current().connection
            val statement = connection.prepareStatement(sql, false)
            val resultSet = statement.executeQuery()

            while (resultSet.next()) {
                val authorityName = resultSet.getString("authority_name")
                val maleCount = resultSet.getInt("male_count")
                val femaleCount = resultSet.getInt("female_count")
                val age0to19Count = resultSet.getInt("age_0_19_count")
                val age20PlusCount = resultSet.getInt("age_20_plus_count")
                val ageUnknownCount = resultSet.getInt("age_unknown_count")

                statistics[authorityName] = maleCount
                statistics["${authorityName}_female"] = femaleCount
                statistics["${authorityName}_age0to19"] = age0to19Count
                statistics["${authorityName}_age20plus"] = age20PlusCount
                statistics["${authorityName}_ageunknown"] = ageUnknownCount
            }
        }
        return@dbQuery statistics
    }

    override suspend fun getUsers(
        familyName: String?,
        firstName: String?,
        authorityId: Int
    ): List<UserInfo> = dbQuery {
        val familyNameCondition = if (!familyName.isNullOrEmpty()) {
            mst_user.familyName.lowerCase() like "%${familyName.lowercase()}%"
        } else {
            Op.TRUE
        }

        val firstNameCondition = if (!firstName.isNullOrEmpty()) {
            mst_user.firstName.lowerCase() like "%${firstName.lowercase()}%"
        } else {
            Op.TRUE
        }

        val authorityIdCondition = if (authorityId != -1) {
            mst_role.authorityId eq authorityId
        } else {
            Op.TRUE
        }

        val query = mst_user
            .leftJoin(mst_role, { mst_user.authorityId }, { mst_role.authorityId })
            .leftJoin(mst_gender, { mst_user.genderId }, { genderId })
            .slice(
                mst_user.userId,
                mst_user.password,
                mst_user.familyName,
                mst_user.firstName,
                mst_user.genderId,
                mst_user.age,
                mst_user.admin,
                mst_user.authorityId,
                mst_role.authorityName,
                mst_gender.genderName
            )
            .select {
                familyNameCondition and firstNameCondition and authorityIdCondition
            }
            .orderBy(mst_user.userId)

        query.mapNotNull { it.toUserInfoTotal() }
    }
}



