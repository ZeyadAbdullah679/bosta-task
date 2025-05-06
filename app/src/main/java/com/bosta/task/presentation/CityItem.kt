package com.bosta.task.presentation

data class CityItem(
    val name: String,
    val districts: List<DistrictItem>,
    var isExpanded: Boolean = false
)