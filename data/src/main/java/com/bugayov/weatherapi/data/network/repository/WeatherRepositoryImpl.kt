package com.bugayov.weatherapi.data.network.repository

import android.graphics.Bitmap
import com.bugayov.weatherapi.data.network.services.WeatherService
import com.bugayov.weatherapi.data.storage.interfaces.LocationStorage
import com.bugayov.weatherapi.domain.models.Weather
import com.bugayov.weatherapi.domain.repository.LocationRepository
import com.bugayov.weatherapi.domain.repository.WeatherRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepositoryImpl(
    private val weatherService: WeatherService
    ) : WeatherRepository {

    override suspend fun getCurrentWeather(city: String): Weather {
        val weatherResponse = weatherService.getCurrentWeather(city)
        return Weather(
            location = city,
            condition = weatherResponse.weather[0].main,
            conditionImage = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888),
            temperature = weatherResponse.main.temp,
            cloudiness = weatherResponse.clouds.all,
            windSpeed = weatherResponse.wind.speed
        )
    }

}