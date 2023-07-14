package com.bugayov.weatherapi.domain.models

import android.graphics.Bitmap

class Weather (
    val location: String?,
    val condition: String,
    val conditionImage: Bitmap,
    val temperature: Double,
    val cloudiness: Int,
    val windSpeed: Double
)