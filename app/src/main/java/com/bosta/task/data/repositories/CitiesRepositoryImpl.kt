package com.bosta.task.data.repositories

import com.bosta.task.data.models.CityDto
import com.bosta.task.data.services.CitiesService
import com.bosta.task.domain.repositories.CitiesRepository
import com.bosta.task.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor(
    private val citiesService: CitiesService
) : CitiesRepository {

    override suspend fun getAllDistricts(): Flow<DataState<List<CityDto>>> {
        return flow {
            try {
                val response = citiesService.getAllDistricts()
                when {
                    response.isSuccessful -> {
                        val body = response.body()
                        if (body?.success == true) {
                            emit(DataState.Success(body.cities))
                        } else {
                            emit(DataState.Error(Exception("Invalid data format or missing fields")))
                        }
                    }

                    else -> emit(DataState.Error(Exception("HTTP ${response.code()}: ${response.message()}")))
                }
            } catch (e: IOException) {
                emit(DataState.Error(Exception("Network issue: ${e.localizedMessage}", e)))
            } catch (e: HttpException) {
                emit(DataState.Error(Exception("HTTP exception: ${e.message}", e)))
            } catch (e: Exception) {
                emit(DataState.Error(Exception("Unexpected error: ${e.localizedMessage}", e)))
            }
        }.flowOn(Dispatchers.IO)
    }
}
