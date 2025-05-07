package com.bosta.task.domain.models

data class District(
    val districtId: String,
    val coverage: String,
    val districtName: String,
    val districtOtherName: String,
    val dropOffAvailability: Boolean,
    val isBusy: Boolean,
    val notAllowedBulkyOrders: Boolean,
    val pickupAvailability: Boolean,
    val zoneId: String,
    val zoneName: String,
    val zoneOtherName: String
)
