package com.bosta.task.data.mappers

import com.bosta.task.data.models.CityDto
import com.bosta.task.data.models.DistrictDto
import com.bosta.task.domain.models.City
import com.bosta.task.domain.models.District

fun CityDto.toCity(): City {
    return City(
        id = cityId,
        name = cityName,
        otherName = cityOtherName,
        zoneId = cityCode,
        zoneName = cityName,
        zoneOtherName = cityOtherName,
        districts = districts.map { it.toDistrict() },
        dropOffAvailability = dropOffAvailability,
        pickupAvailability = pickupAvailability
    )
}

fun DistrictDto.toDistrict(): District {
    return District(
        districtId = districtId,
        districtName = districtName,
        districtOtherName = districtOtherName,
        zoneId = zoneId,
        zoneName = zoneName,
        zoneOtherName = zoneOtherName,
        notAllowedBulkyOrders = notAllowedBulkyOrders ?: false,
        dropOffAvailability = dropOffAvailability,
        pickupAvailability = pickupAvailability,
        isBusy = isBusy ?: false,
        coverage = coverage
    )
}
