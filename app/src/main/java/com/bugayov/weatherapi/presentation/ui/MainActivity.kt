package com.bugayov.weatherapi.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.bugayov.weatherapi.domain.models.Weather
import com.bugayov.weatherapi.presentation.ui.styles.MainTypography
import com.bugayov.weatherapi.presentation.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val vm by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherView()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun WeatherView() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val weather = vm.weather.observeAsState()
            val error = vm.error.observeAsState()
            val city = vm.city.observeAsState()

            if (weather.value != null) {
                WeatherCard(weather = weather.value!!)
            } else if (error.value != null) {
                Text(
                    text = error.value!!
                )
            }

            TextField(
                value = city.value ?: "",
                onValueChange = vm::updateCity,
                label = { Text("City") }
            )

            Button(onClick = { vm.updateWeather() }) {
                Text("Update weather")
            }

            if (error.value != null) {
                Text(
                    text = error.value!!,
                    style = MainTypography.labelSmall.copy(
                        color = Color.Red
                    )
                )
            }
        }
    }

    @Composable
    fun WeatherCard(weather: Weather) {
        Text(
            text = "Location: ${weather.location}",
            style = MainTypography.labelMedium
        )
        ProvideTextStyle(value = MainTypography.labelSmall) {
            Text(text = "Condition: ${weather.condition}",)
            Text(text = "Temperature: ${weather.temperature}")
            Text(text = "Wind speed: ${weather.windSpeed}")
            Text(text = "Cloudiness: ${weather.cloudiness}")
        }
        Image(
            bitmap = weather.conditionImage.asImageBitmap(),
            contentDescription = "condition image",
            modifier = Modifier.size(75.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
    }


}