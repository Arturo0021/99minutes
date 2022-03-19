package com.example.test.model

import com.google.gson.annotations.SerializedName

data class PlaceModelDetails(
        @SerializedName("results") val results : List<PlaceModelResultsDetails>
)

data class PlaceModelResultsDetails(
        @SerializedName("geometry") val geometry : itemsLocationsdetails,
        @SerializedName("name") val name : String,
        @SerializedName("vicinity") val vicinity : String,
        @SerializedName("place_id") val place_id : String,
        @SerializedName("icon") val icon : String,
        @SerializedName("url") val url : String
)

data class itemsLocationsdetails(
        @SerializedName("location") val location : locationsdetails
)

data class locationsdetails(
        @SerializedName("lat") val lat : Double,
        @SerializedName("lng") val lng : Double
)
