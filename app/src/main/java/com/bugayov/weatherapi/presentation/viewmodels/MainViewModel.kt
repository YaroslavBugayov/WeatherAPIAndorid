package com.bugayov.weatherapi.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bugayov.weatherapi.data.storage.models.Location
import com.bugayov.weatherapi.domain.models.Weather
import com.bugayov.weatherapi.domain.usecases.GetCurrentWeatherUseCase
import com.bugayov.weatherapi.domain.usecases.GetLocationUseCase
import com.bugayov.weatherapi.domain.usecases.SetLocationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val setLocationUseCase: SetLocationUseCase,
    private val getLocationUseCase: GetLocationUseCase
) : ViewModel() {

    private val weatherMutable = MutableLiveData<Weather>()
    val weather: LiveData<Weather> = weatherMutable

    fun saveLocation(city: String) {
        setLocationUseCase.execute(city)
        updateWeather()
    }

    fun updateWeather() {
        val city = getLocationUseCase.execute()
        viewModelScope.launch {
            try {
                val weather = getCurrentWeatherUseCase.execute(city)
                weatherMutable.value = weather
            } catch (e: Exception) {
                throw Exception(e)
            }
        }
    }
}