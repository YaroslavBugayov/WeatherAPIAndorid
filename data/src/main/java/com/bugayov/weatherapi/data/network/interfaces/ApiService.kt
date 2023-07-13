package com.bugayov.weatherapi.data.network.interfaces

import com.bugayov.weatherapi.data.network.models.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("current")
    suspend fun getCurrentWeather(
        @Query("city") city: String,
        @Query("apiKey") apiKey: String
    ) : WeatherResponse
}