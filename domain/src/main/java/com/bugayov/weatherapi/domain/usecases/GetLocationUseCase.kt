package com.bugayov.weatherapi.domain.usecases

import com.bugayov.weatherapi.domain.repository.LocationRepository

class GetLocationUseCase(private val locationRepository: LocationRepository) {

    fun execute() : String {
        return locationRepository.getLocation()
    }
}