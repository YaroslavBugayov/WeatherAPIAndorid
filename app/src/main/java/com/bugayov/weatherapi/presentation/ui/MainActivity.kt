package com.bugayov.weatherapi.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bugayov.weatherapi.R
import com.bugayov.weatherapi.presentation.factories.MainViewModelFactory
import com.bugayov.weatherapi.presentation.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProvider(this, MainViewModelFactory(this))[MainViewModel::class.java]
        vm.weather.observe(this, Observer{
            findViewById<TextView>(R.id.textView).text = it.location
            findViewById<TextView>(R.id.textView2).text = it.condition
            findViewById<TextView>(R.id.textView3).text = it.temperature.toString()
        })

        findViewById<Button>(R.id.button).setOnClickListener {
            vm.saveLocation(findViewById<EditText>(R.id.editText).text.toString())
        }
    }
}