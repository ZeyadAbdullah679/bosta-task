package com.bosta.task.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CitiesResponse(
    @SerialName("data")
    val cities: List<CityDto>,
    @SerialName("message")
    val message: String,
    @SerialName("success")
    val success: Boolean
)