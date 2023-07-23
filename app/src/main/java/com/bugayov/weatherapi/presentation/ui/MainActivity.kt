package com.bugayov.weatherapi.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bugayov.weatherapi.R
import com.bugayov.weatherapi.databinding.ActivityMainBinding
import com.bugayov.weatherapi.presentation.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val vm by viewModel<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        vm.weather.observe(this, Observer{
            binding.textLocation.text = getString(R.string.location_output, it.location)
            binding.textCondition.text = getString(R.string.condition_output, it.condition)
            binding.textTemperature.text = getString(R.string.temperature_output, it.temperature)
            binding.textWindSpeed.text = getString(R.string.windSpeed_output, it.windSpeed)
            binding.textCloudiness.text = getString(R.string.cloudiness_output, it.cloudiness)
            binding.imageView.setImageBitmap(it.conditionImage)
        })

        vm.error.observe(this, Observer{ error ->
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        })

        vm.updateWeather()

        binding.setLocationButton.setOnClickListener {
            vm.saveLocation(binding.editText.text.toString())
        }
    }

}