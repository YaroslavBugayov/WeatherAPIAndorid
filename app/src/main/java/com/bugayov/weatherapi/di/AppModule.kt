package com.bugayov.weatherapi.di

import com.bugayov.weatherapi.presentation.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val appModule = module {

    viewModel<MainViewModel> {
        MainViewModel(
            getCurrentWeatherUseCase = get(),
            setLocationUseCase = get(),
            getLocationUseCase = get()
        )
    }
}