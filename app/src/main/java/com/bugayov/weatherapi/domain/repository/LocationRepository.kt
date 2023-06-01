package com.bugayov.weatherapi.domain.repository

interface LocationRepository {
    fun saveLocation(location: String)
}