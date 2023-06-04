package com.bugayov.weatherapi.di

import com.bugayov.weatherapi.domain.usecases.GetCurrentWeatherUseCase
import com.bugayov.weatherapi.domain.usecases.SetLocationUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetCurrentWeatherUseCase> {
        GetCurrentWeatherUseCase(weatherRepository = get())
    }

    factory<SetLocationUseCase> {
        SetLocationUseCase(locationRepository = get())
    }
}