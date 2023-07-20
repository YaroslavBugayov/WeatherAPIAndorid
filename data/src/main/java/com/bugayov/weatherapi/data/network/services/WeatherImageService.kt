package com.bugayov.weatherapi.data.network.services

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.bugayov.weatherapi.data.network.api.WeatherImageApi
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class WeatherImageService {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService: WeatherImageApi = retrofit.create(WeatherImageApi::class.java)

    suspend fun getWeatherImage(icon: String): Bitmap {
        val call = apiService.getWeatherImage(icon)
        Log.d("Request", call.request().toString())

        val response = call.awaitResponse()
        if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                val inputStream = responseBody.byteStream()
                return BitmapFactory.decodeStream(inputStream)
            } else {
                throw Exception("Image not found")
            }
        } else {
            throw RuntimeException(response.message())
        }
    }

}