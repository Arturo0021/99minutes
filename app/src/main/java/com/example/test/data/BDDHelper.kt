package com.example.test.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.test.model.DataModel

class BDDHelper (context: Context, val DB_Name : String = DB_NAME) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        var  DB_NAME = "TestSqlite"
        private val DB_VERSION = 1

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.run {
            execSQL(" DROP TABLE IF EXISTS " + DataModel().Tbl_Search + "; ")
            execSQL(" DROP TABLE IF EXISTS " + DataModel().Tbl_Places + "; ")
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        Table_Search(db!!)
        Table_Places(db)
    }

    private fun Table_Search(db : SQLiteDatabase){
        val query: String =
            "CREATE TABLE IF NOT EXISTS ${DataModel().Tbl_Search} ( " +
                    DataModel().Id                + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    DataModel().Search            + " VARCHAR(250) " +
                    ");"
        db.execSQL(query)
    }

    private fun Table_Places(db : SQLiteDatabase) {
        val query: String =
            "CREATE TABLE IF NOT EXISTS ${DataModel().Tbl_Places} ( " +
                    DataModel().Id                + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    DataModel().Search            + " VARCHAR(250), " +
                    DataModel().Icon            + " VARCHAR(250), " +
                    DataModel().Url            + " VARCHAR(250), " +
                    DataModel().Vicinity            + " VARCHAR(250), " +
                    DataModel().Name            + " VARCHAR(250), " +
                    DataModel().Lat            + " VARCHAR(250), " +
                    DataModel().Lng            + " VARCHAR(250), " +
                    DataModel().PlaceId            + " VARCHAR(250) " +
                    ");"
        db.execSQL(query)
    }

}