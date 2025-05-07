package com.bosta.task.domain.repositories

import com.bosta.task.data.models.CityDto
import com.bosta.task.utils.DataState
import kotlinx.coroutines.flow.Flow

interface CitiesRepository {
    suspend fun getAllDistricts(): Flow<DataState<List<CityDto>>>
}