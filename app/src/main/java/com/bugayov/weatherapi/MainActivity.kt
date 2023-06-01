package com.bugayov.weatherapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bugayov.weatherapi.domain.usecases.GetCurrentWeatherUseCase

class MainActivity : AppCompatActivity() {

    private val getCurrentWeatherUseCase = GetCurrentWeatherUseCase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weather = getCurrentWeatherUseCase.execute()
        findViewById<TextView>(R.id.textView).text = weather.location
        findViewById<TextView>(R.id.textView2).text = weather.condition
        findViewById<TextView>(R.id.textView3).text = weather.temperature.toString()
    }
}