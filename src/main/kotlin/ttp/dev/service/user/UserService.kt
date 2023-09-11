package ttp.dev.service.user

import ttp.dev.data.model.Params.UpdateUserParams
import ttp.dev.data.model.UserInfo

interface UserService {
    suspend fun getUserByID(userID: String): UserInfo?
    suspend fun updateUser(user: UpdateUserParams): Int
    suspend fun deleteUser(userId: String, loggedInUserId: String): Int
    suspend fun isUserExists(userId: String): Boolean
    suspend fun getStatistics(): Map<String, Int>
    suspend fun getUsers(familyName: String?, firstName: String?, authorityId: Int): List<UserInfo?>
}