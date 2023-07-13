package com.bugayov.weatherapi.domain.usecases

import android.graphics.Bitmap
import com.bugayov.weatherapi.domain.models.Weather
import com.bugayov.weatherapi.domain.repository.WeatherRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestRepository: WeatherRepository {
    override fun getCurrentWeather(city: String): Weather {
        return Weather(
            location = "TestLocation",
            condition = "Sun",
            conditionImage = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888),
            temperature = 20.0,
            cloudiness = 10.0,
            windSpeed = 10.0
        )
    }
}

class GetCurrentWeatherUseCaseTest {

    @Test
    fun `should return data from repository`() {
        val testRepository = TestRepository()
        val useCase = GetCurrentWeatherUseCase(testRepository)
        val expected = Weather(
            location = "TestLocation",
            condition = "Sun",
            conditionImage = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888),
            temperature = 20.0,
            cloudiness = 10.0,
            windSpeed = 10.0
        )

        val actual = useCase.execute("TestLocation")

        Assertions.assertEquals(expected, actual)
    }
}