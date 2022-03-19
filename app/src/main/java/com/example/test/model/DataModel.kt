package com.example.test.model

import android.database.sqlite.SQLiteDatabase
import com.example.test.data.BDDHelper
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import android.content.Context

class DataModel : Serializable {

    @SerializedName("DB_NAME")    val DB_NAME = "TestFast"
    @SerializedName("DB_VERSION") val DB_VERSION = 1

    // Tables
    @SerializedName("Tbl_Search") val Tbl_Search = "Tbl_Search"
    @SerializedName("Tbl_Places") val Tbl_Places = "Tbl_Places"

    // Columns
    @SerializedName("Id") val Id = "Id"
    @SerializedName("Search") val Search = "Search"

    @SerializedName("Icon") val Icon = "Icon"
    @SerializedName("Url") val Url = "Url"
    @SerializedName("Vicinity") val Vicinity = "Vicinity"
    @SerializedName("Name") val Name = "Name"
    @SerializedName("Lat") val Lat = "Lat"
    @SerializedName("Lng") val Lng = "Lng"
    @SerializedName("PlaceId") val PlaceId = "PlaceId"

}

class database() {
    fun Repository(context: Context?): SQLiteDatabase {
        val helper = BDDHelper(context!!)
        return helper.writableDatabase
    }
}