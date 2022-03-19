package com.example.test.data

import com.example.test.model.PlaceModel
import com.example.test.model.PlaceModelDetails
import com.example.test.model.PlaceService

class PlaceRepository(query : String) {

    private val api = PlaceService(query)

    suspend fun getAllPlaces() : PlaceModel {
        val response : PlaceModel = api.getPlaces()
            PlaceProvider.places = response
        return response
    }

    suspend fun getDetails() : PlaceModelDetails {
        val response : PlaceModelDetails = api.getDetails()
        PlaceProviderDetails.placesDetails = response
        return response
    }
}