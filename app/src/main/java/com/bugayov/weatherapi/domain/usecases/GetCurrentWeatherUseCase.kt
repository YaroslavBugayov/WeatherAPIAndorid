package com.bugayov.weatherapi.domain.usecases

import android.graphics.Bitmap
import com.bugayov.weatherapi.domain.models.Weather

class GetCurrentWeatherUseCase {
    fun execute() : Weather {
        return Weather(
            location = "London",
            condition = "Sun",
            conditionImage = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888),
            temperature = 23.0,
            cloudiness = 10.0,
            windSpeed = 10.0
        )
    }
}