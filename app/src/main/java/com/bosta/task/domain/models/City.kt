package com.bosta.task.domain.models

data class City(
    val id: String,
    val name: String,
    val otherName: String,
    val zoneId: String,
    val zoneName: String,
    val zoneOtherName: String,
    val districts: List<District>,
    val dropOffAvailability: Boolean,
    val pickupAvailability: Boolean
)
