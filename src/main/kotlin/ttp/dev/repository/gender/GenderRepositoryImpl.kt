package ttp.dev.repository.gender

import ttp.dev.base.BaseResponse
import ttp.dev.config.SUCCESS
import ttp.dev.service.gender.GenderService

class GenderRepositoryImpl(private val genderService: GenderService) : GenderRepository {
    override suspend fun getGender(): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = genderService.getGender(), message = SUCCESS)
    }
}