package ttp.dev.extensions

import org.jetbrains.exposed.sql.ResultRow
import ttp.dev.data.model.Gender
import ttp.dev.data.model.Role
import ttp.dev.data.model.User
import ttp.dev.data.model.UserInfo
import ttp.dev.data.table.mst_gender
import ttp.dev.data.table.mst_role
import ttp.dev.data.table.mst_user

fun ResultRow?.toUser(): User? {
    return if (this == null) null
    else User(
        userId = this[mst_user.userId],
        password = this[mst_user.password],
        familyName = this[mst_user.familyName],
        firstName = this[mst_user.firstName],
        genderId = this[mst_user.genderId],
        age = this[mst_user.age],
        authorityId = this[mst_user.authorityId],
        admin = this[mst_user.admin],
        createUserId = this[mst_user.createUserId],
        updateUserId = this[mst_user.updateUserId],
        createDate = this[mst_user.createDate],
        updateDate = this[mst_user.updateDate],
        fullName = "${this[mst_user.familyName]} ${this[mst_user.firstName]}"
    )
}

fun ResultRow?.toUserInfo(): UserInfo? {
    return if (this == null) null
    else UserInfo(
        userId = this[mst_user.userId],
        password = this[mst_user.password],
        familyName = this[mst_user.familyName],
        firstName = this[mst_user.firstName],
        genderId = this[mst_user.genderId],
        age = this[mst_user.age],
        authorityId = this[mst_user.authorityId],
        admin = this[mst_user.admin],
        fullName = "${this[mst_user.familyName]} ${this[mst_user.firstName]}"
    )
}

fun ResultRow?.toUserInfoTotal(): UserInfo? {
    return if (this == null) null
    else UserInfo(
        userId = this[mst_user.userId],
        password = this[mst_user.password],
        familyName = this[mst_user.familyName],
        firstName = this[mst_user.firstName],
        genderId = this[mst_user.genderId],
        age = this[mst_user.age],
        authorityId = this[mst_user.authorityId],
        admin = this[mst_user.admin],
        genderName = this[mst_gender.genderName],
        authorityName = this[mst_role.authorityName],
        fullName = "${this[mst_user.familyName]} ${this[mst_user.firstName]}"
    )
}

fun ResultRow?.toRole(): Role? {
    return if (this == null) null
    else Role(
        authorityId = this[mst_role.authorityId],
        authorityName = this[mst_role.authorityName],
        createUserId = this[mst_role.createUserId],
        updateUserId = this[mst_role.updateUserId],
        createDate = this[mst_role.createDate],
        updateDate = this[mst_role.updateDate],
    )
}

fun ResultRow?.toGender(): Gender? {
    return if (this == null) null
    else Gender(
        genderId = this[mst_gender.genderId],
        genderName = this[mst_gender.genderName],
        createUserId = this[mst_gender.createUserId],
        updateUserId = this[mst_gender.updateUserId],
        createDate = this[mst_gender.createDate],
        updateDate = this[mst_gender.updateDate],
    )
}