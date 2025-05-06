package com.bosta.task.data.services

import com.bosta.task.data.models.CitiesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CitiesService {
    @GET("cities/getAllDistricts")
    suspend fun getAllDistricts(
        @Query("countryId") countryId: String = "60e4482c7cb7d4bc4849c4d5"
    ): Response<CitiesResponse>
}