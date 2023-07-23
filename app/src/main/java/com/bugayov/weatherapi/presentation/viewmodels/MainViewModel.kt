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
import com.bugayov.weatherapi.domain.utils.Resource
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

    private val errorMutable = MutableLiveData<String?>(null)
    val error: LiveData<String?> = errorMutable

    fun updateWeather() {
        val city = getLocationUseCase.execute()
        viewModelScope.launch {
            when (val result = getCurrentWeatherUseCase.execute(city)) {
                is Resource.Success -> weatherMutable.value = result.data as Weather
                is Resource.Error -> errorMutable.value = result.message
            }
        }
    }

    fun updateWeather(city: String) {
        viewModelScope.launch {
            when (val result = getCurrentWeatherUseCase.execute(city)) {
                is Resource.Success -> {
                    weatherMutable.value = result.data as Weather
                    setLocationUseCase.execute(city)
                }
                is Resource.Error -> errorMutable.value = result.message
            }
        }
    }
}