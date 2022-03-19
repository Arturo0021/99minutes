package com.example.test.model

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import com.example.test.viewmodel.LocationViewModel
import com.example.test.viewmodel.PlaceViewModel
import com.google.android.gms.location.*

class LocationService() : Service() {
    private var isforeground = false
    private var mFusedLocationProviderClient: FusedLocationProviderClient? = null
    private val INTERVAL: Long = 3 * 60 * 1000
    private lateinit var mLastLocation: Location
    private lateinit var mLocationRequest: LocationRequest
    private lateinit var locationModel: LocationModel

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()
        mLocationRequest = LocationRequest()
        startLocationUpdates()
    }

    @SuppressLint("MissingPermission")
    protected fun startLocationUpdates() {
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = INTERVAL
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY;
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest)
        val locationSettingsRequest = builder.build()
        val settingsclient = LocationServices.getSettingsClient(this@LocationService)
        settingsclient.checkLocationSettings(locationSettingsRequest)
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this@LocationService)
        mFusedLocationProviderClient!!.requestLocationUpdates(
            mLocationRequest,
            mLocationCallback,
            Looper.myLooper()!!
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            super.onLocationResult(p0)
            onLocationChanged(p0.lastLocation)
        }

        @SuppressLint("MissingPermission")
        override fun onLocationAvailability(p0: LocationAvailability) {
            if (!p0.isLocationAvailable()) {
                var locationManager: LocationManager =
                    getSystemService(Context.LOCATION_SERVICE) as LocationManager
                var i = Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            }
        }
    }

    /*private fun distanciasdeunpuntoaotro(location: Location) {
        val visitaLocation = Location("")
            visitaLocation.setLatitude(visita.Latitud!!)
            visitaLocation.setLongitude(visita.Longitud!!)
        val distanciaUltimaTiendaVisitada = location.distanceTo(visitaLocation)
    }*/

    fun stopLocationUpdate() {
        mFusedLocationProviderClient!!.removeLocationUpdates(mLocationCallback)
    }

    fun onLocationChanged(location: Location) {  // aquí tomamos la localización
        mLastLocation = location
        Log.v("#LOG", location.toString())
    }

    fun getLocation() : LocationModel {
        locationModel = LocationModel("", 0.0, 0.0, "")

        locationModel.latitudtext  = mLastLocation.latitude
        locationModel.longitudtext = mLastLocation.longitude
        return locationModel
    }

    override fun onDestroy() {
        super.onDestroy()
        stopLocationUpdate()
        if (isforeground)
            stopForeground(true)
    }
}