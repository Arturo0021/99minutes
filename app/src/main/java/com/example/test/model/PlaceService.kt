package com.example.test.model

import com.example.test.core.RetrofitHelper
import com.example.test.data.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class PlaceService(val query : String) {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getPlaces() : PlaceModel {
        return withContext(Dispatchers.IO){
            val response: Response<PlaceModel> = retrofit.create(ApiClient::class.java).getAllPlaceModel("19.461716,-99.275012", "1500", query, "AIzaSyAcSsBlIoj5Jl-EB7ok1Ni-tGn8ljS7DcE")
            response.body()!!
        }
    }

    suspend fun getDetails() : PlaceModelDetails {
        return withContext(Dispatchers.IO){
            val response: Response<PlaceModelDetails> = retrofit.create(ApiClient::class.java).getDetails(query, "AIzaSyAcSsBlIoj5Jl-EB7ok1Ni-tGn8ljS7DcE")
            response.body()!!
        }
    }

}