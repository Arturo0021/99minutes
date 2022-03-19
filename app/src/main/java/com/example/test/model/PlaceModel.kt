package com.example.test.model

import com.google.gson.annotations.SerializedName

data class PlaceModel(
        @SerializedName("results") val results : List<PlaceModelResults>
)

data class PlaceModelResults(
        @SerializedName("geometry") val geometry : itemsLocations,
        @SerializedName("name") val name : String,
        @SerializedName("vicinity") val vicinity : String,
        @SerializedName("place_id") val place_id : String,
        @SerializedName("icon") val icon : String,
        @SerializedName("url") val Url : String
)

data class itemsLocations(
        @SerializedName("location") val location : locations
)

data class locations(
        @SerializedName("lat") val lat : Double,
        @SerializedName("lng") val lng : Double
)
