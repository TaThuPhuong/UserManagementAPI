package ttp.dev.data.table

import org.jetbrains.exposed.sql.Table

object mst_gender : Table() {
    val genderId = integer("gender_id")
    val genderName = varchar("gender_name", 10)
    val createUserId = varchar("create_user_id", 8)
    val updateUserId = varchar("update_user_id", 8)
    val createDate = long("create_date")
    val updateDate = long("update_date")

    override val primaryKey = PrimaryKey(genderId)
}