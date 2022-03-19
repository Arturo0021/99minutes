package com.example.test.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.GetLocationUse
import com.example.test.model.LocationModel
import kotlinx.coroutines.launch

class LocationViewModel : ViewModel() {

    val locationModel = MutableLiveData<LocationModel>()
    var getLocationUse = GetLocationUse()

    fun onCreate() {
        viewModelScope.launch {
            var result = getLocationUse()
            locationModel.postValue(result)
        }
    }

}