package com.example.test.domain

import com.example.test.data.LocationRepository
import com.example.test.model.LocationModel

class GetLocationUse() {

    private val repository = LocationRepository()

    suspend operator fun invoke(): LocationModel = repository.getLocation()

}