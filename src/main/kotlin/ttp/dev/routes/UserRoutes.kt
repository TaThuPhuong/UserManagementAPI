package ttp.dev.routes

import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ttp.dev.repository.user.UserRepository
import io.ktor.server.resources.post
import io.ktor.server.resources.get
import ttp.dev.data.model.Params.UpdateUserParams

@Resource(ALL_USER_REQUEST)
class GetAllUser

@Resource(STATISTICS_REQUEST)
class GetStatisticsUser

@Resource(USERS)
class GetUserById

@Resource(DELETE_REQUEST)
class DeleteUser

@Resource(UPDATE_REQUEST)
class UpdateUser

fun Application.userRouting(repository: UserRepository) {


    routing {
        authenticate {
            get<GetAllUser> {
                val familyName = call.request.queryParameters["familyName"] ?: ""
                val firstName = call.request.queryParameters["firstName"] ?: ""
                val authorityId = call.request.queryParameters["authorityId"]?.toIntOrNull() ?: -1
                val result = repository.getUsers(familyName, firstName, authorityId)
                call.respond(result.statusCode, result)
            }

            get<GetStatisticsUser> {
                val result = repository.getStatistics()
                call.respond(result.statusCode, result)
            }

            get<GetUserById> {
                val userID = call.request.queryParameters["userID"] ?: ""
                val result = repository.getUserByID(userID)
                call.respond(result.statusCode, result)
            }

            post<DeleteUser> {
                val userID = call.request.queryParameters["userID"] ?: ""
                val loggedInUserId = call.request.queryParameters["userIDLogin"] ?: ""
                val result = repository.deleteUser(userID, loggedInUserId)
                call.respond(result.statusCode, result)
            }

            post<UpdateUser> {
                val params = call.receive<UpdateUserParams>()
                val result = repository.updateUser(params)
                call.respond(result.statusCode, result)
            }
        }
    }

}