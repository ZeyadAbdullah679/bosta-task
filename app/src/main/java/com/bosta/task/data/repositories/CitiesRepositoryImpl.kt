package com.bosta.task.data.repositories

import com.bosta.task.data.models.CityDto
import com.bosta.task.data.services.CitiesService
import com.bosta.task.domain.repositories.CitiesRepository
import com.bosta.task.utils.DataState
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor(
    private val citiesService: CitiesService
) : CitiesRepository {

    override suspend fun getAllDistricts(): DataState<List<CityDto>> {
        return try {
            val response = citiesService.getAllDistricts()
            when {
                response.isSuccessful -> {
                    val body = response.body()
                    if (body?.success == true) {
                        DataState.Success(body.cities)
                    } else {
                        DataState.Error(Exception("Invalid data format or missing fields"))
                    }
                }
                else -> DataState.Error(Exception("HTTP ${response.code()}: ${response.message()}"))
            }
        } catch (e: IOException) {
            DataState.Error(Exception("Network issue: ${e.localizedMessage}", e))
        } catch (e: HttpException) {
            DataState.Error(Exception("HTTP exception: ${e.message}", e))
        } catch (e: Exception) {
            DataState.Error(Exception("Unexpected error: ${e.localizedMessage}", e))
        }
    }
}
