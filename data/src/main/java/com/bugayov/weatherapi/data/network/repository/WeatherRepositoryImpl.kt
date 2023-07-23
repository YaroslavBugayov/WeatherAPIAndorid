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
        return when (val weatherResponse = weatherService.getCurrentWeather(city)) {
            is Resource.Error -> Resource.Error("City not found")
            is Resource.Success -> {
                val response = weatherResponse.data!!
                val conditionImage = weatherImageService
                    .getWeatherImage(response.weather[0].icon)
                Resource.Success(Weather(
                    location = city,
                    condition = response.weather[0].main,
                    conditionImage = conditionImage,
                    temperature = response.main.temp,
                    cloudiness = response.clouds.all,
                    windSpeed = response.wind.speed
                ))
            }
        }
    }

}