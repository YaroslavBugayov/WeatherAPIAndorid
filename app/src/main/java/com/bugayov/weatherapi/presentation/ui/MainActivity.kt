package com.bugayov.weatherapi.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
            binding.textView.text = it.location
            binding.textView2.text = it.condition
            binding.textView3.text = it.temperature.toString()
        })

        vm.updateWeather()

        binding.setLocationButton.setOnClickListener {
            vm.saveLocation(binding.editText.text.toString())
        }
    }
}