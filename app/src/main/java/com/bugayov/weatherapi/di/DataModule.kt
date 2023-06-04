package com.bugayov.weatherapi.di

import com.bugayov.weatherapi.data.repository.LocationRepositoryImpl
import com.bugayov.weatherapi.data.repository.WeatherRepositoryImpl
import com.bugayov.weatherapi.data.storage.interfaces.LocationStorage
import com.bugayov.weatherapi.data.storage.sharedprefs.SharedPrefLocationStorage
import com.bugayov.weatherapi.domain.repository.LocationRepository
import com.bugayov.weatherapi.domain.repository.WeatherRepository
import org.koin.dsl.module

val dataModule = module {
    single<LocationStorage> {
        SharedPrefLocationStorage(context = get())
    }

    single<LocationRepository> {
        LocationRepositoryImpl(locationStorage = get())
    }

    single<WeatherRepository> {
        WeatherRepositoryImpl(locationStorage = get())
    }
}