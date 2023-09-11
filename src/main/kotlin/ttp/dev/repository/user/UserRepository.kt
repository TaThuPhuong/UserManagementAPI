package ttp.dev.repository.user

import ttp.dev.base.BaseResponse
import ttp.dev.data.model.Params.UpdateUserParams

interface UserRepository {
    suspend fun updateUser(params: UpdateUserParams): BaseResponse<Any>
    suspend fun getUserByID(userID: String): BaseResponse<Any>
    suspend fun deleteUser(userId: String, loggedInUserId: String): BaseResponse<Any>
    suspend fun isUserExists(userId: String): BaseResponse<Any>
    suspend fun getStatistics(): BaseResponse<Any>
    suspend fun getUsers(familyName: String?, firstName: String?, authorityId: Int): BaseResponse<Any>
}