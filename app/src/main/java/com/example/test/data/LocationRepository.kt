package com.example.test.data

import com.example.test.model.LocationModel
import com.example.test.model.LocationService

class LocationRepository() {

    private val location = LocationService()

    suspend fun getLocation() : LocationModel {
        val response : LocationModel = location.getLocation()
        return response
    }

}