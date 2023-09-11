package ttp.dev.service.gender

import org.jetbrains.exposed.sql.selectAll
import ttp.dev.data.model.Gender
import ttp.dev.data.table.mst_gender
import ttp.dev.extensions.toGender
import ttp.dev.repository.DatabaseFactory.dbQuery

class GenderServiceImpl : GenderService {
    override suspend fun getGender(): List<Gender?> = dbQuery{
        mst_gender.selectAll().map { resultRow ->
            resultRow.toGender()
        }
    }
}