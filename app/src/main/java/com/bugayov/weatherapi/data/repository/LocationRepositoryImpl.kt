package com.bugayov.weatherapi.data.repository

import com.bugayov.weatherapi.data.storage.models.Location
import com.bugayov.weatherapi.data.storage.interfaces.LocationStorage
import com.bugayov.weatherapi.domain.repository.LocationRepository

class LocationRepositoryImpl(private val locationStorage: LocationStorage) : LocationRepository {

    override fun saveLocation(location: String) {
        locationStorage.saveLocation(Location(location))
    }

}