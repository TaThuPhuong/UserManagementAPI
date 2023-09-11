package ttp.dev.routes

import io.ktor.resources.*

const val API_VERSION = "/v1"

//auth
const val AUTH = "$API_VERSION/auth"
const val REGISTER_REQUEST = "$AUTH/register"
const val LOGIN_REQUEST = "$AUTH/login"

//user
const val USERS = "$API_VERSION/users"
const val ALL_USER_REQUEST = "$USERS/all"
const val STATISTICS_REQUEST = "$USERS/statistics"
const val DELETE_REQUEST = "$USERS/delete"
const val UPDATE_REQUEST = "$USERS/update"

//other
const val GENDER = "$API_VERSION/gender"
const val ROLE = "$API_VERSION/role"

@Resource(REGISTER_REQUEST)
class UserRegisterRoute

@Resource(LOGIN_REQUEST)
class UserLoginRoute

