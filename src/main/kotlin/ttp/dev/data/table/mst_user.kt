package ttp.dev.data.table

import org.jetbrains.exposed.sql.Table

object mst_user : Table() {
    val userId = varchar("user_id", 8)
    val password = varchar("password", 8)
    val familyName = varchar("family_name", 10)
    val firstName = varchar("first_name", 10)
    val genderId = integer("gender_id")
    val age = integer("age").nullable()
    val authorityId = integer("authority_id").nullable()
    val admin = integer("admin")
    val createUserId = varchar("create_user_id", 8)
    val updateUserId = varchar("update_user_id", 8)
    val createDate = long("create_date")
    val updateDate = long("update_date")

    override val primaryKey = PrimaryKey(userId)
}