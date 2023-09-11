package ttp.dev.service.role

import org.jetbrains.exposed.sql.selectAll
import ttp.dev.data.model.Role
import ttp.dev.data.table.mst_role
import ttp.dev.extensions.toRole
import ttp.dev.repository.DatabaseFactory.dbQuery

class RoleServiceImpl : RoleService {
    override suspend fun getRole(): List<Role?> = dbQuery {
        mst_role.selectAll().map { resultRow ->
            resultRow.toRole()
        }
    }
}