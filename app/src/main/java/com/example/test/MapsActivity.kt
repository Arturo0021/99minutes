package com.example.test

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.lifecycle.Observer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.example.test.databinding.ActivityMapsBinding
import com.example.test.model.LocationModel
import com.example.test.viewmodel.PlaceViewModel
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener,
    GoogleMap.InfoWindowAdapter {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private var mapFragment: SupportMapFragment? = null
    private var Lat: Double? = 0.0
    private var Long: Double? = 0.0
    private val placeviewmodel: PlaceViewModel by viewModels()
    private var query : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this@MapsActivity)
        getLocation()

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initmap()
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        val myLocation = LatLng(Lat!!, Long!!)
        mMap = googleMap
        mMap.isMyLocationEnabled = true
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        mMap.uiSettings.isMyLocationButtonEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = false
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 13.4f))
    }

        override fun onInfoWindowClick(p0: Marker) {
            TODO("Not yet implemented")
        }

        override fun getInfoContents(p0: Marker): View? {
            TODO("Not yet implemented")
        }

        override fun getInfoWindow(p0: Marker): View? {
            TODO("Not yet implemented")
        }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        val locationRequest = LocationRequest.create();
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY;
        locationRequest.interval = 1000;
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                if (locationResult.locations.isEmpty()) {
                    return;
                }
                for (location in locationResult.getLocations()) {
                    if (location != null) {
                        Lat = location.latitude
                        Long = location.longitude
                        mapFragment!!.getMapAsync(this@MapsActivity)
                        mFusedLocationClient.removeLocationUpdates(this)
                    }
                }
            }
        }

        mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback,
            Looper.myLooper()!!
        )

        placeviewmodel.placeModel.observe(this@MapsActivity, Observer {
            for(PlaceModel in it.results){
                mMap.addMarker(
                    MarkerOptions()
                        .position(LatLng(PlaceModel.geometry.location.lat, PlaceModel.geometry.location.lng))
                        .title(PlaceModel.name)
                        .snippet(PlaceModel.vicinity)
                )
            }
        })
    }

    private fun initmap() {
        mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment!!.getMapAsync(this)
    }
}