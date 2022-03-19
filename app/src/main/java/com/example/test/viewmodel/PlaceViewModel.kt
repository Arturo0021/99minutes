package com.example.test.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.GetPlacesUse
import com.example.test.domain.GetPlacesUseDetail
import com.example.test.model.PlaceModel
import com.example.test.model.PlaceModelDetails
import kotlinx.coroutines.launch

class PlaceViewModel : ViewModel() {

    val placeModel = MutableLiveData<PlaceModel>()
    val isLoading = MutableLiveData<Boolean>()

    private var valor : String = ""

    fun onCreate(query : String) {
        this.valor = query
        val getPlaceUse = GetPlacesUse(this.valor)

        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getPlaceUse()

            placeModel.postValue(result)
            isLoading.postValue(false)
        }
    }
}

class PlaceViewModelDetails : ViewModel() {

    private val placeModelDetails = MutableLiveData<PlaceModelDetails>()
    private val isLoading = MutableLiveData<Boolean>()

    private var valor : String = ""

    fun onCreateDetails(query : String) {
        this.valor = query
        val getPlaceUseDetails = GetPlacesUseDetail(this.valor)

        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getPlaceUseDetails()

            placeModelDetails.postValue(result)
            isLoading.postValue(false)
        }
    }
}