package com.bugayov.weatherapi.data.network.services

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.bugayov.weatherapi.data.network.api.WeatherApi
import com.bugayov.weatherapi.data.network.models.WeatherResponse
import com.bugayov.weatherapi.domain.utils.Resource
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.RuntimeException

class WeatherService {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService: WeatherApi = retrofit.create(WeatherApi::class.java)

    suspend fun getCurrentWeather(city: String): Resource<WeatherResponse> {
        val call = apiService.getCurrentWeather(
            city = city,
            apiKey = "7dc565aa823fc68a9efee11052595cb9",
            units = "metric"
        )
        Log.d("Request", call.request().toString())

        val response = call.awaitResponse()
        return if (response.isSuccessful) {
            if (response.code() == 404) {
                return Resource.Error("City not found")
            } else {
                return Resource.Success(response.body())
            }
        } else {
            Resource.Error("Something went wrong")
        }
    }
}