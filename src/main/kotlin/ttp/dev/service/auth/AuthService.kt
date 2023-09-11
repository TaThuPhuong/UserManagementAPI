package ttp.dev.service.auth

import ttp.dev.data.model.Params.RegisterUserParams
import ttp.dev.data.model.User

interface AuthService{
    suspend fun registerUser(params: RegisterUserParams): User?
    suspend fun findByUserID(userID: String): User?
    suspend fun loginUser(userID: String, password: String): User?
}