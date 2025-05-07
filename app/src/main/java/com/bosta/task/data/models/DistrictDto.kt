package com.bosta.task.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DistrictDto(
    @SerialName("coverage")
    val coverage: String,
    @SerialName("districtId")
    val districtId: String,
    @SerialName("districtName")
    val districtName: String,
    @SerialName("districtOtherName")
    val districtOtherName: String,
    @SerialName("dropOffAvailability")
    val dropOffAvailability: Boolean,
    @SerialName("isBusy")
    val isBusy: Boolean? = null,
    @SerialName("notAllowedBulkyOrders")
    val notAllowedBulkyOrders: Boolean? = null,
    @SerialName("pickupAvailability")
    val pickupAvailability: Boolean,
    @SerialName("zoneId")
    val zoneId: String,
    @SerialName("zoneName")
    val zoneName: String,
    @SerialName("zoneOtherName")
    val zoneOtherName: String
)