package com.bugayov.weatherapi.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bugayov.weatherapi.domain.models.Weather
import com.bugayov.weatherapi.domain.usecases.GetCurrentWeatherUseCase
import com.bugayov.weatherapi.domain.usecases.GetLocationUseCase
import com.bugayov.weatherapi.domain.usecases.SetLocationUseCase
import com.bugayov.weatherapi.domain.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val setLocationUseCase: SetLocationUseCase,
    private val getLocationUseCase: GetLocationUseCase
) : ViewModel() {

    private val _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather> = _weather

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    private val _city = MutableLiveData<String?>(null)
    val city: LiveData<String?> = _city

    init {
        loadWeather()
    }

    fun loadWeather() {
        val city = getLocationUseCase.execute()
        findWeather(city)
    }

    fun updateWeather() {
        val city = _city.value
        if (city != null) {
            findWeather(city)
        } else {
            _error.value = "City is empty"
        }
    }

    fun updateCity(city: String) {
        _city.value = city.capitalize()
    }

    private fun findWeather(city: String) {
        viewModelScope.launch {
            when (val result = getCurrentWeatherUseCase.execute(city)) {
                is Resource.Success -> {
                    _weather.value = result.data as Weather
                    setLocationUseCase.execute(city)
                    _error.value = null
                }
                is Resource.Error -> _error.value = result.message
            }
        }
    }
}