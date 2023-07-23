package com.bugayov.weatherapi.data.network.repository

import com.bugayov.weatherapi.data.network.services.WeatherImageService
import com.bugayov.weatherapi.data.network.services.WeatherService
import com.bugayov.weatherapi.domain.models.Weather
import com.bugayov.weatherapi.domain.repository.WeatherRepository
import com.bugayov.weatherapi.domain.utils.Resource

class WeatherRepositoryImpl(
    private val weatherService: WeatherService,
    private val weatherImageService: WeatherImageService
    ) : WeatherRepository {

    override suspend fun getCurrentWeather(city: String): Resource<Weather> {
        val weatherResponse = weatherService.getCurrentWeather(city)
        val conditionImage = weatherImageService.getWeatherImage(weatherResponse.weather[0].icon)
        return Resource.Success(Weather(
            location = city,
            condition = weatherResponse.weather[0].main,
            conditionImage = conditionImage,
            temperature = weatherResponse.main.temp,
            cloudiness = weatherResponse.clouds.all,
            windSpeed = weatherResponse.wind.speed
        ))
    }

}