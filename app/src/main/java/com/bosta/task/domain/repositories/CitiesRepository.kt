package com.bosta.task.domain.repositories

import com.bosta.task.data.models.CityDto
import com.bosta.task.utils.DataState

interface CitiesRepository {
    suspend fun getAllDistricts(): DataState<List<CityDto>>
}