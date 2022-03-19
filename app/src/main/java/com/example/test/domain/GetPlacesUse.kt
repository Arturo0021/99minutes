package com.example.test.domain

import com.example.test.data.PlaceRepository
import com.example.test.model.PlaceModel
import com.example.test.model.PlaceModelDetails

class GetPlacesUse (query : String) {

    private val repository = PlaceRepository(query)

    suspend operator fun invoke():PlaceModel = repository.getAllPlaces()

}

class GetPlacesUseDetail(query: String) {

    private val repository = PlaceRepository(query)

    suspend operator fun invoke(): PlaceModelDetails = repository.getDetails()

}
