package ttp.dev.repository.role

import ttp.dev.base.BaseResponse

interface RoleRepository {
    suspend fun getRole(): BaseResponse<Any>
}