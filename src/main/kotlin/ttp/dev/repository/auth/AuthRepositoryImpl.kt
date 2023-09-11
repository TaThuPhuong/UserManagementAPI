package ttp.dev.repository.auth

import ttp.dev.base.BaseResponse
import ttp.dev.config.SUCCESS
import ttp.dev.config.USER_LOGIN_FAILURE
import ttp.dev.data.model.Params.RegisterUserParams
import ttp.dev.data.model.Params.UserLoginParams
import ttp.dev.security.JwtConfig
import ttp.dev.service.auth.AuthService

class AuthRepositoryImpl(private val authService: AuthService) : AuthRepository {

    override suspend fun registerUser(params: RegisterUserParams): BaseResponse<Any> {
        return if (isUserIdExist(params.userId)) {
            BaseResponse.ErrorResponse(message = "UserID đã tồn tại")
        } else {
            val user = authService.registerUser(params)
            if (user != null) {
                val token = JwtConfig.instance.createAccessToken(user.userId)
                user.authToken = token
                BaseResponse.SuccessResponse(data = user, message = SUCCESS)
            } else {
                BaseResponse.ErrorResponse(message = "Đã xảy ra lỗi trong quá trình đăng kí!")
            }
        }
    }

    override suspend fun loginUser(params: UserLoginParams): BaseResponse<Any> {
        if(params.userId.length > 8 || params.password.length > 8){
            return BaseResponse.ErrorResponse(message = USER_LOGIN_FAILURE)
        }
        val user = authService.loginUser(params.userId, params.password)
        return if (user != null) {
            val token = JwtConfig.instance.createAccessToken(user.userId)
            user.authToken = token
            BaseResponse.SuccessResponse(data = user, message = SUCCESS)
        } else {
            BaseResponse.ErrorResponse(message = USER_LOGIN_FAILURE)
        }
    }

    private suspend fun isUserIdExist(userID: String): Boolean {
        return authService.findByUserID(userID) != null
    }
}
