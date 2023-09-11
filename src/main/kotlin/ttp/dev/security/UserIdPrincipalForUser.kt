package ttp.dev.security

import io.ktor.server.auth.*

data class UserIdPrincipalForUser(val userId: String) : Principal
