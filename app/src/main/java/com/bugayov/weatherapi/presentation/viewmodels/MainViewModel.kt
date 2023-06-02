package com.bugayov.weatherapi.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bugayov.weatherapi.domain.models.Weather
import com.bugayov.weatherapi.domain.usecases.GetCurrentWeatherUseCase
import com.bugayov.weatherapi.domain.usecases.SetLocationUseCase

class MainViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val setLocationUseCase: SetLocationUseCase
) : ViewModel() {

    val weather = MutableLiveData<Weather>()

    fun saveLocation(location: String) {
        setLocationUseCase.execute(location)
        weather.value = getCurrentWeatherUseCase.execute()
    }

    fun updateWeather() {
        weather.value = getCurrentWeatherUseCase.execute()
    }

}