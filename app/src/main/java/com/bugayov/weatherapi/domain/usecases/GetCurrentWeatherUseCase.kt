package com.bugayov.weatherapi.domain.usecases

import android.graphics.Bitmap
import android.location.Location
import com.bugayov.weatherapi.domain.models.Weather
import com.bugayov.weatherapi.domain.repository.WeatherRepository

class GetCurrentWeatherUseCase(private val weatherRepository: WeatherRepository) {
    fun execute(location: String) : Weather {
        return weatherRepository.getCurrentWeather(location)
    }
}