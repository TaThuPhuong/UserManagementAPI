package ttp.dev.config

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import ttp.dev.security.*
import ttp.dev.security.UserIdPrincipalForUser

fun Application.configureSecurity() {

    JwtConfig.initialize("my-app")

    install(Authentication) {
        jwt {
            verifier(JwtConfig.instance.verifier)
            validate {
                val claim = it.payload.getClaim(JwtConfig.CLAIM).asString()
                if (claim != null) {
                    UserIdPrincipalForUser(claim)
                } else {
                    null
                }
            }

        }
    }
}
