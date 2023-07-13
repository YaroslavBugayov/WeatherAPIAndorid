package com.bugayov.weatherapi.data.network.services

import android.content.res.Resources
import com.bugayov.data.R
import com.bugayov.weatherapi.data.network.interfaces.ApiService
import com.bugayov.weatherapi.data.network.models.WeatherResponse
import com.bugayov.weatherapi.data.storage.interfaces.LocationStorage
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApiService {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService: ApiService = retrofit.create(ApiService::class.java)

    suspend fun getCurrentWeather(locationStorage: LocationStorage): WeatherResponse {
        return apiService.getCurrentWeather(
            city = locationStorage.getLocation().city,
            apiKey = Resources.getSystem().getString(R.string.api_key)
        )
    }
}