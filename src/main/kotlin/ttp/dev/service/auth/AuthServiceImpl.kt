package ttp.dev.service.auth

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import ttp.dev.data.model.Params.RegisterUserParams
import ttp.dev.data.model.User
import ttp.dev.data.table.mst_user
import ttp.dev.extensions.toUser
import ttp.dev.repository.DatabaseFactory.dbQuery
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AuthServiceImpl : AuthService {
    override suspend fun registerUser(params: RegisterUserParams): User? = dbQuery {
        val insertStatement = mst_user.insert {
            it[userId] = params.userId
            it[password] = params.password
            it[familyName] = params.familyName
            it[firstName] = params.firstName
            it[genderId] = params.genderId
            it[age] = params.age
            it[authorityId] = params.authorityId
            it[admin] = params.admin
            it[createUserId] = params.createUserId
            it[updateUserId] = params.createUserId
            it[createDate] = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")).toLong()
            it[updateDate] = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")).toLong()
        }
        insertStatement.resultedValues?.singleOrNull()?.let { resultRow -> resultRow.toUser() }
    }

    override suspend fun findByUserID(userID: String): User? = dbQuery {
        mst_user
            .select { mst_user.userId eq userID }
            .map { resultRow -> resultRow.toUser() }.singleOrNull()
    }

    override suspend fun loginUser(userID: String, password: String): User? = dbQuery {
        mst_user
            .select { (mst_user.userId eq userID) and (mst_user.password eq password) }
            .firstOrNull().toUser()
    }
}