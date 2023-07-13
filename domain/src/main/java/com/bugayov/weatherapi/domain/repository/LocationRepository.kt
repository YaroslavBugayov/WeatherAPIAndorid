package com.bugayov.weatherapi.domain.repository

import android.location.Location

interface LocationRepository {
    fun saveLocation(location: String)
    fun getLocation() : String
}