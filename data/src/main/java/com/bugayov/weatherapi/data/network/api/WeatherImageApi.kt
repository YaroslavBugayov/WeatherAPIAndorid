package com.bugayov.weatherapi.data.network.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherImageApi {
    @GET("img/wn/{icon}.png")
    fun getWeatherImage(
        @Path("icon") icon: String
    ) : Call<ResponseBody>
}