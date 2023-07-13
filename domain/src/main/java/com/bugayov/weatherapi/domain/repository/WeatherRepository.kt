package com.bugayov.weatherapi.domain.repository

import com.bugayov.weatherapi.domain.models.Weather

interface WeatherRepository {

    fun getCurrentWeather(city: String): Weather
}