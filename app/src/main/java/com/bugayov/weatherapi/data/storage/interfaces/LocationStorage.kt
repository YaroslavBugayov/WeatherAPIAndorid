package com.bugayov.weatherapi.data.storage.interfaces

import com.bugayov.weatherapi.data.storage.models.Location

interface LocationStorage {
    fun saveLocation(location: Location)
    fun getLocation(): Location
}