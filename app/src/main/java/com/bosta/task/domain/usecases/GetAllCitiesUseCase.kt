package com.bosta.task.domain.usecases

import com.bosta.task.domain.repositories.CitiesRepository
import javax.inject.Inject

class GetAllCitiesUseCase @Inject constructor(
    private val citiesRepository: CitiesRepository
) {
    suspend operator fun invoke() = citiesRepository.getAllDistricts()
}