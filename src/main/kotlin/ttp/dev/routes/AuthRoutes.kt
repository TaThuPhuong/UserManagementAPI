package ttp.dev.routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.resources.post
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ttp.dev.data.model.Params.RegisterUserParams
import ttp.dev.data.model.Params.UserLoginParams
import ttp.dev.repository.auth.AuthRepository

fun Application.authRoutes(repository: AuthRepository) {

    routing {
        post<UserRegisterRoute> {
            val params = call.receive<RegisterUserParams>()
            val result = repository.registerUser(params)
            call.respond(result.statusCode, result)
        }

        post<UserLoginRoute> {
            val params = call.receive<UserLoginParams>()
            val result = repository.loginUser(params)
            call.respond(result.statusCode, result)
        }
    }
}