package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.test.adapter.CustomAdapterList
import com.example.test.data.BDDSelect
import com.example.test.databinding.ActivityDetailsBinding
import com.example.test.databinding.ActivityMainBinding
import com.example.test.model.Details
import com.example.test.model.LocationModel
import com.example.test.view.Layout
import com.example.test.viewmodel.PlaceViewModel
import com.example.test.viewmodel.PlaceViewModelDetails

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailsBinding
    private val placeviewmodel: PlaceViewModelDetails by viewModels()
    private lateinit var model : Details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val placeid = intent.extras!!.get("placeid")
        try {
            placeviewmodel.onCreateDetails(placeid.toString())
        }catch (e: Exception){
            Log.v("#TAG", e.message.toString())
        }




        model = BDDSelect(this@DetailsActivity).getPlaces(placeid.toString())

            binding.name.text = model.name
            binding.vicinity.text = model.vicinity
            binding.latlong.text = "Coordenadas: Latitud: (${model.lat}) Longitud: (${model.lng})"
            binding.url.text = model.url



    }
}