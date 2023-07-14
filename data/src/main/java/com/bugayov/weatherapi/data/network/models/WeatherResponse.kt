package com.bugayov.weatherapi.data.network.models

class WeatherResponse(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val id: Long,
    val name: String,
    val cod: Int
)

class Coord(
    val lon: Double,
    val lat: Double
)

class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int
)

class Wind(
    val speed: Double,
    val deg: Int,
    val gust: Double
)

class Clouds(
    val all: Int
)

class Sys(
    val type: Int,
    val id: Int,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)