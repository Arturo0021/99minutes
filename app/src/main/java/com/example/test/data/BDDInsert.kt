package com.example.test.data

import android.content.Context
import com.example.test.model.*
import java.util.ArrayList

class BDDInsert(val context : Context) {

    fun insertSearch(textquery : String) {

        val query = "SELECT COUNT(1) FROM " + DataModel().Tbl_Search + " WHERE " + DataModel().Search + " = '" + textquery + "';"
        val cursor = database().Repository(context).rawQuery(query, null)
        val list : ArrayList<LocationModel> = ArrayList<LocationModel>()
        if(cursor.moveToFirst()) {
            val existe = cursor.getInt(0)
            if(existe == 0){
                val insert = "INSERT INTO ${DataModel().Tbl_Search} (" +
                        " ${DataModel().Search} " +
                        " ) VALUES (" +
                        "'" + textquery + "'" +
                        ");"
                database().Repository(context).execSQL(insert)
            }
        }
        cursor.close()
    }

    fun insertResults(places : PlaceModelResults, search : String) {

        val query = "SELECT COUNT(1) FROM " + DataModel().Tbl_Places + " WHERE " + DataModel().PlaceId + " = '" + places.place_id + "';"
        val cursor = database().Repository(context).rawQuery(query, null)
        if(cursor.moveToFirst()) {
            val existe = cursor.getInt(0)
            if(existe == 0){
                val insert = "INSERT INTO ${DataModel().Tbl_Places} (" +
                                " ${DataModel().Search}, " +
                                " ${DataModel().Icon}, " +
                                " ${DataModel().Url}, " +
                                " ${DataModel().Vicinity}, " +
                                " ${DataModel().Name}, " +
                                " ${DataModel().Lat}, " +
                                " ${DataModel().Lng}, " +
                                " ${DataModel().PlaceId} " +
                        " ) VALUES (" +
                                "'" + search + "'," +
                                "'" + places.icon + "'," +
                                "'" + places.Url + "'," +
                                "'" + places.vicinity + "'," +
                                "'" + places.name + "'," +
                                "'" + places.geometry.location.lat + "'," +
                                "'" + places.geometry.location.lng + "'," +
                                "'" + places.place_id + "'" +
                        ");"
                database().Repository(context).execSQL(insert)
            }
        }
    }

}