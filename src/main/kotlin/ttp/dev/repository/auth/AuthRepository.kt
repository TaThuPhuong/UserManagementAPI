package ttp.dev.repository.auth

import ttp.dev.data.model.Params.RegisterUserParams
import ttp.dev.base.BaseResponse
import ttp.dev.data.model.Params.UserLoginParams

interface AuthRepository {
    suspend fun registerUser(params: RegisterUserParams): BaseResponse<Any>
    suspend fun loginUser(params: UserLoginParams): BaseResponse<Any>
}