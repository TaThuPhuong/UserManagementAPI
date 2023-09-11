package ttp.dev.repository.gender

import ttp.dev.base.BaseResponse

interface GenderRepository {
    suspend fun getGender(): BaseResponse<Any>
}