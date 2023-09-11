package ttp.dev.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val userId: String,
    val password: String,
    val familyName: String,
    val firstName: String,
    val genderId: Int,
    val age: Int?,
    val authorityId: Int?,
    val admin: Int,
    val createUserId: String,
    val updateUserId: String,
    val createDate: Long,
    val updateDate: Long,
    val genderName: String? = null,
    val authorityName: String? = null,
    val fullName: String,
    var authToken: String? = null
)

data class UserInfo(
    val userId: String,
    val password: String,
    val familyName: String,
    val firstName: String,
    val genderId: Int,
    val age: Int?,
    val authorityId: Int?,
    val admin: Int,
    val genderName: String? = null,
    val authorityName: String? = null,
    val fullName: String
)


