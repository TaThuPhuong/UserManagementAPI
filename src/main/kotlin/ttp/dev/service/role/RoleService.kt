package ttp.dev.service.role

import ttp.dev.data.model.Role

interface RoleService {
    suspend fun getRole(): List<Role?>
}