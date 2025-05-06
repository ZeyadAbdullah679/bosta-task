package com.bosta.task.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityDto(
    @SerialName("cityCode")
    val cityCode: String,
    @SerialName("cityId")
    val cityId: String,
    @SerialName("cityName")
    val cityName: String,
    @SerialName("cityOtherName")
    val cityOtherName: String,
    @SerialName("districts")
    val districts: List<DistrictDto>,
    @SerialName("dropOffAvailability")
    val dropOffAvailability: Boolean,
    @SerialName("pickupAvailability")
    val pickupAvailability: Boolean
)