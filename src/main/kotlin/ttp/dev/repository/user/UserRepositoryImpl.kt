package ttp.dev.repository.user

import ttp.dev.base.BaseResponse
import ttp.dev.config.DELETE_FAILURE
import ttp.dev.config.DeleteUserErrorCodes
import ttp.dev.config.DeleteUserErrorCodes.DELETE_ERR
import ttp.dev.config.DeleteUserErrorCodes.INVALID_USER
import ttp.dev.config.SUCCESS
import ttp.dev.config.UPDATE_FAILURE
import ttp.dev.config.UserExist.EXIST
import ttp.dev.config.UserExist.NOT_EXIST
import ttp.dev.data.model.Params.UpdateUserParams
import ttp.dev.service.user.UserService

class UserRepositoryImpl(private val userService: UserService) : UserRepository {

    override suspend fun updateUser(params: UpdateUserParams): BaseResponse<Any> {
        return when (userService.updateUser(params)) {
            1 -> {
                BaseResponse.SuccessResponse(data = 1, message = SUCCESS)
            }

            else -> {
                BaseResponse.ErrorResponse(message = UPDATE_FAILURE)
            }
        }
    }

    override suspend fun getUserByID(userID: String): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = userService.getUserByID(userID), message = SUCCESS)
    }

    override suspend fun deleteUser(userId: String, loggedInUserId: String): BaseResponse<Any> {
        when (userService.deleteUser(userId, loggedInUserId)) {
            INVALID_USER -> { //data = -1 là trùng User
                return BaseResponse.SuccessResponse(data = -1, message = SUCCESS)
            }

            DeleteUserErrorCodes.SUCCESS -> { //data = 1 là thành công
                return BaseResponse.SuccessResponse(data = 1, message = SUCCESS)
            }

            DELETE_ERR -> { //data = 0 là lỗi
                return BaseResponse.SuccessResponse(data = 0, message = DELETE_FAILURE)
            }

            else -> {
                return BaseResponse.ErrorResponse(message = DELETE_FAILURE)
            }
        }
    }

    override suspend fun isUserExists(userId: String): BaseResponse<Any> {
        if (userService.isUserExists(userId)) {
            return BaseResponse.SuccessResponse(data = null, message = EXIST)
        }
        return BaseResponse.ErrorResponse(message = NOT_EXIST)
    }

    override suspend fun getStatistics(): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = userService.getStatistics(), message = SUCCESS)
    }

    override suspend fun getUsers(familyName: String?, firstName: String?, authorityId: Int): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(
            data = userService.getUsers(familyName, firstName, authorityId),
            message = SUCCESS
        )
    }
}