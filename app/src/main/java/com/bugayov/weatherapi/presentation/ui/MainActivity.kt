package com.bugayov.weatherapi.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import com.bugayov.weatherapi.R
import com.bugayov.weatherapi.presentation.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val vm by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text(text = "Hello world")
        }

        vm.weather.observe(this, Observer{
//            binding.textLocation.text = getString(R.string.location_output, it.location)
//            binding.textCondition.text = getString(R.string.condition_output, it.condition)
//            binding.textTemperature.text = getString(R.string.temperature_output, it.temperature)
//            binding.textWindSpeed.text = getString(R.string.windSpeed_output, it.windSpeed)
//            binding.textCloudiness.text = getString(R.string.cloudiness_output, it.cloudiness)
//            binding.imageView.setImageBitmap(it.conditionImage)
        })

        vm.error.observe(this, Observer{ error ->
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        })

        vm.updateWeather()

//        binding.setLocationButton.setOnClickListener {
//            vm.updateWeather(binding.editText.text.toString())
//        }
    }

    @Composable
    fun WeatherCard(city: String) {
        Text(city)
    }

    @Preview
    @Composable
    fun PreviewWeatherCard() {
        WeatherCard(city = "London")
    }

}