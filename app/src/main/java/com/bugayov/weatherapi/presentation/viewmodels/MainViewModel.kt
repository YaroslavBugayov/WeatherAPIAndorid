package com.bugayov.weatherapi.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bugayov.weatherapi.domain.models.Weather
import com.bugayov.weatherapi.domain.usecases.GetCurrentWeatherUseCase
import com.bugayov.weatherapi.domain.usecases.SetLocationUseCase

class MainViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val setLocationUseCase: SetLocationUseCase
) : ViewModel() {

    private val weatherMutable = MutableLiveData<Weather>()
    val weather: LiveData<Weather> = weatherMutable

    fun saveLocation(location: String) {
        setLocationUseCase.execute(location)
        weatherMutable.value = getCurrentWeatherUseCase.execute()
    }

    fun updateWeather() {
        weatherMutable.value = getCurrentWeatherUseCase.execute()
    }
}