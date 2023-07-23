package com.bugayov.weatherapi.domain.repository

import com.bugayov.weatherapi.domain.models.Weather
import com.bugayov.weatherapi.domain.utils.Resource

interface WeatherRepository {

    suspend fun getCurrentWeather(city: String): Resource<Weather>
}