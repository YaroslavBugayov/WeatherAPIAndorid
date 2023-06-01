package com.bugayov.weatherapi.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bugayov.weatherapi.R
import com.bugayov.weatherapi.data.repository.WeatherRepositoryImpl
import com.bugayov.weatherapi.domain.usecases.GetCurrentWeatherUseCase

class MainActivity : AppCompatActivity() {

    private val weatherRepository by lazy(LazyThreadSafetyMode.NONE) { WeatherRepositoryImpl() }
    private val getCurrentWeatherUseCase by lazy(LazyThreadSafetyMode.NONE) { GetCurrentWeatherUseCase(weatherRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weather = getCurrentWeatherUseCase.execute("London")
        findViewById<TextView>(R.id.textView).text = weather.location
        findViewById<TextView>(R.id.textView2).text = weather.condition
        findViewById<TextView>(R.id.textView3).text = weather.temperature.toString()
    }
}