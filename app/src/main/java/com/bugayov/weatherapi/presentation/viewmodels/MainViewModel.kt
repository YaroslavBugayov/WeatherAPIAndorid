package com.bugayov.weatherapi.presentation.viewmodels

import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bugayov.weatherapi.R
import com.bugayov.weatherapi.data.repository.LocationRepositoryImpl
import com.bugayov.weatherapi.data.repository.WeatherRepositoryImpl
import com.bugayov.weatherapi.data.storage.sharedprefs.SharedPrefLocationStorage
import com.bugayov.weatherapi.domain.models.Weather
import com.bugayov.weatherapi.domain.repository.LocationRepository
import com.bugayov.weatherapi.domain.usecases.GetCurrentWeatherUseCase

class MainViewModel(
    private val getCurrentWeather: GetCurrentWeatherUseCase,
    private val locationRepository: LocationRepository
) : ViewModel() {

    val weather = MutableLiveData<Weather>()

    fun saveLocation(location: String) {
        locationRepository.saveLocation(location)
        weather.value = getCurrentWeather.execute()
    }

}