package ttp.dev.service.gender

import ttp.dev.data.model.Gender

interface GenderService {
    suspend fun getGender(): List<Gender?>
}