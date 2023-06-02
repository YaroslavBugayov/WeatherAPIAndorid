package com.bugayov.weatherapi.domain.usecases

import com.bugayov.weatherapi.domain.repository.LocationRepository

class SetLocationUseCase(private val locationRepository: LocationRepository) {
    fun execute(location: String) {
        locationRepository.saveLocation(location)
    }
}