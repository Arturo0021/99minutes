package com.example.test.data

import com.example.test.model.PlaceModel
import com.example.test.model.PlaceModelDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {

        @GET("nearbysearch/json")
        suspend fun getAllPlaceModel(
                @Query("location") lat : String,
                @Query("radius")   radius : String,
                @Query("type")     query : String,
                @Query("key")      key : String
        ): Response<PlaceModel>


        @GET("details/json")
        suspend fun getDetails(
                @Query("placeid") placeid : String,
                @Query("key")   key : String
        ) : Response<PlaceModelDetails>
}