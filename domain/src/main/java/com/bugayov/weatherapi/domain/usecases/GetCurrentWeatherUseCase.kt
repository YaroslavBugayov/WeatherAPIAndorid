package com.bugayov.weatherapi.domain.usecases

import com.bugayov.weatherapi.domain.models.Weather
import com.bugayov.weatherapi.domain.repository.WeatherRepository
import com.bugayov.weatherapi.domain.utils.Resource

class GetCurrentWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend fun execute(city: String) : Resource<Weather> {
        return if (city.isEmpty()) {
            Resource.Error("City is empty")
        } else {
            weatherRepository.getCurrentWeather(city)
        }
    }

}