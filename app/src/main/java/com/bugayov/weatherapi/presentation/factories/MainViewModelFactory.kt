package com.bugayov.weatherapi.presentation.factories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bugayov.weatherapi.data.repository.LocationRepositoryImpl
import com.bugayov.weatherapi.data.repository.WeatherRepositoryImpl
import com.bugayov.weatherapi.data.storage.sharedprefs.SharedPrefLocationStorage
import com.bugayov.weatherapi.domain.usecases.GetCurrentWeatherUseCase
import com.bugayov.weatherapi.domain.usecases.SetLocationUseCase
import com.bugayov.weatherapi.presentation.viewmodels.MainViewModel

class MainViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val locationStorage by lazy(LazyThreadSafetyMode.NONE) {
        SharedPrefLocationStorage(context)
    }
    private val weatherRepository by lazy(LazyThreadSafetyMode.NONE) {
        WeatherRepositoryImpl(locationStorage)
    }
    private val locationRepository by lazy(LazyThreadSafetyMode.NONE) {
        LocationRepositoryImpl(locationStorage)
    }

    private val getCurrentWeatherUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetCurrentWeatherUseCase(weatherRepository)
    }
    private val setLocationUseCase by lazy(LazyThreadSafetyMode.NONE) {
        SetLocationUseCase(locationRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getCurrentWeatherUseCase, setLocationUseCase) as T
    }
}