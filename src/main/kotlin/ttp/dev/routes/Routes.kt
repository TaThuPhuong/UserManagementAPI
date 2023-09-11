package ttp.dev.routes

import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ttp.dev.repository.gender.GenderRepository
import ttp.dev.repository.role.RoleRepository

@Resource(GENDER)
class GetGenderRoute

@Resource(ROLE)
class GetRoleRoute

fun Application.genderRouting(repository: GenderRepository) {
    routing {
        authenticate {
            get<GetGenderRoute> {
                val result = repository.getGender()
                call.respond(result.statusCode, result)
            }
        }
    }
}

fun Application.roleRouting(repository: RoleRepository) {
    routing {
        authenticate {
            get<GetRoleRoute> {
                val result = repository.getRole()
                call.respond(result.statusCode, result)
            }
        }
    }
}