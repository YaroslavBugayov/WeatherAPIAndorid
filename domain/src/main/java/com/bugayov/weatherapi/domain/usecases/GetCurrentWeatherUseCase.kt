package com.bugayov.weatherapi.domain.usecases

import com.bugayov.weatherapi.domain.models.Weather
import com.bugayov.weatherapi.domain.repository.WeatherRepository

class GetCurrentWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend fun execute(city: String) : Weather {
        return weatherRepository.getCurrentWeather(city)
    }
}