package com.example.test.model

import com.google.gson.annotations.SerializedName

data class LocationModel(
    @SerializedName("place_id") var place_id : String,
    @SerializedName("latitudtext") var latitudtext : Double,
    @SerializedName("longitudtext") var longitudtext : Double,
    @SerializedName("busquedas") var busquedas : String
)

data class Details(
    @SerializedName("name")     var name : String,
    @SerializedName("vicinity") var vicinity : String,
    @SerializedName("place_id") var place_id : String,
    @SerializedName("icon")     var icon : String,
    @SerializedName("url")      var url : String,
    @SerializedName("lat")      var lat : Double,
    @SerializedName("lng")      var lng : Double
)