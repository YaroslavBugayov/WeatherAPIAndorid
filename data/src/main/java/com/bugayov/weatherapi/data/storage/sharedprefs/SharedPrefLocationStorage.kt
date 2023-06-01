package com.bugayov.weatherapi.data.storage.sharedprefs

import android.content.Context
import com.bugayov.weatherapi.data.storage.interfaces.LocationStorage
import com.bugayov.weatherapi.data.storage.models.Location

private const val SHARED_PREF_KEY = "shared"
private const val KEY_LOCATION = "location"
private const val DEFAULT_LOCATION = "London"

class SharedPrefLocationStorage(context: Context) : LocationStorage {

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)

    override fun saveLocation(location: Location) {
        sharedPreferences.edit().putString(KEY_LOCATION, location.location).apply()
    }

    override fun getLocation(): Location {
        val res = sharedPreferences.getString(KEY_LOCATION, DEFAULT_LOCATION) ?: DEFAULT_LOCATION
        return Location(res)
    }

}