package com.example.test.data

import android.content.Context
import com.example.test.model.*
import java.util.ArrayList

class BDDSelect(val context: Context) {

    fun getSearch() : ArrayList<LocationModel> {
        val query = "SELECT * FROM " + DataModel().Tbl_Search
        val cursor = database().Repository(context).rawQuery(query, null)
        val lista : ArrayList<LocationModel> = ArrayList<LocationModel>()
        if(cursor.moveToFirst()) {
            do {
                val model = LocationModel("", 0.0, 0.0, "")
                    model.busquedas = cursor.getString(1)
                    lista.add(model)
            } while(cursor.moveToNext())
        }
        cursor.close()
        return lista
    }

    fun getSearchText(valquery : String) : ArrayList<LocationModel> {
        val query = "SELECT * FROM " + DataModel().Tbl_Search + " WHERE " + DataModel().Search + " LIKE '%" + valquery + "%';"
        val cursor = database().Repository(context).rawQuery(query, null)
        val list : ArrayList<LocationModel> = ArrayList<LocationModel>()
        if(cursor.moveToFirst()) {
            do {
                val model = LocationModel("", 0.0, 0.0, "")
                model.busquedas = cursor.getString(1)
                list.add(model)
            } while(cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    fun getPlaces(valquery : String) : Details {
        val query = "SELECT * FROM " + DataModel().Tbl_Places + " WHERE " + DataModel().PlaceId + " LIKE '%" + valquery + "%';"
        val cursor = database().Repository(context).rawQuery(query, null)
        val model = Details("","","","","",0.0,0.0)
        if(cursor.moveToFirst()) {
            do {
                    model.icon     = cursor.getString(2)
                    model.url      = cursor.getString(3)
                    model.vicinity = cursor.getString(4)
                    model.name     = cursor.getString(5)
                    model.lat      = cursor.getDouble(6)
                    model.lng      = cursor.getDouble(7)
                    model.place_id = cursor.getString(8)
            } while(cursor.moveToNext())
        }
        cursor.close()
        return model
    }

}