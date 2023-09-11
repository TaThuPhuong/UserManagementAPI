package ttp.dev.repository.role

import ttp.dev.base.BaseResponse
import ttp.dev.config.SUCCESS
import ttp.dev.service.role.RoleService

class RoleRepositoryImpl(private val roleService: RoleService) : RoleRepository {
    override suspend fun getRole(): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = roleService.getRole(), message = SUCCESS)
    }
}