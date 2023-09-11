package ttp.dev.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Role(
    val authorityId: Int,
    val authorityName: String,
    val createUserId: String,
    val updateUserId: String,
    val createDate: Long,
    val updateDate: Long,
)