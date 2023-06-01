package com.bugayov.weatherapi.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.bugayov.weatherapi.R
import com.bugayov.weatherapi.data.repository.LocationRepositoryImpl
import com.bugayov.weatherapi.data.repository.WeatherRepositoryImpl
import com.bugayov.weatherapi.data.storage.sharedprefs.SharedPrefLocationStorage
import com.bugayov.weatherapi.domain.usecases.GetCurrentWeatherUseCase

class MainActivity : AppCompatActivity() {

    private val locationStorage by lazy(LazyThreadSafetyMode.NONE) { SharedPrefLocationStorage(applicationContext) }
    private val weatherRepository by lazy(LazyThreadSafetyMode.NONE) { WeatherRepositoryImpl(locationStorage) }
    private val locationRepository by lazy(LazyThreadSafetyMode.NONE) { LocationRepositoryImpl(locationStorage) }
    private val getCurrentWeatherUseCase by lazy(LazyThreadSafetyMode.NONE) { GetCurrentWeatherUseCase(weatherRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weather = getCurrentWeatherUseCase.execute()
        findViewById<TextView>(R.id.textView).text = weather.location
        findViewById<TextView>(R.id.textView2).text = weather.condition
        findViewById<TextView>(R.id.textView3).text = weather.temperature.toString()

        findViewById<Button>(R.id.button).setOnClickListener {
            locationRepository.saveLocation(
                findViewById<EditText>(R.id.editText).text.toString()
            )
        }
    }
}