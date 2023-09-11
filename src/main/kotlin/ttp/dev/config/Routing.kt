package ttp.dev.config

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ttp.dev.repository.auth.AuthRepository
import ttp.dev.repository.auth.AuthRepositoryImpl
import ttp.dev.repository.gender.GenderRepository
import ttp.dev.repository.gender.GenderRepositoryImpl
import ttp.dev.repository.role.RoleRepository
import ttp.dev.repository.role.RoleRepositoryImpl
import ttp.dev.repository.user.UserRepository
import ttp.dev.repository.user.UserRepositoryImpl
import ttp.dev.routes.authRoutes
import ttp.dev.routes.genderRouting
import ttp.dev.routes.roleRouting
import ttp.dev.routes.userRouting
import ttp.dev.service.auth.AuthService
import ttp.dev.service.auth.AuthServiceImpl
import ttp.dev.service.gender.GenderService
import ttp.dev.service.gender.GenderServiceImpl
import ttp.dev.service.role.RoleService
import ttp.dev.service.role.RoleServiceImpl
import ttp.dev.service.user.UserService
import ttp.dev.service.user.UserServiceImpl

fun Application.configureRouting() {
    install(Resources)

    val authService: AuthService = AuthServiceImpl()
    val authRepository: AuthRepository = AuthRepositoryImpl(authService)

    val userService: UserService = UserServiceImpl()
    val userRepository: UserRepository = UserRepositoryImpl(userService)

    val genderService: GenderService = GenderServiceImpl()
    val genderRepository: GenderRepository = GenderRepositoryImpl(genderService)

    val roleService: RoleService = RoleServiceImpl()
    val roleRepository: RoleRepository = RoleRepositoryImpl(roleService)

    authRoutes(authRepository)
    userRouting(userRepository)
    genderRouting(genderRepository)
    roleRouting(roleRepository)
}
