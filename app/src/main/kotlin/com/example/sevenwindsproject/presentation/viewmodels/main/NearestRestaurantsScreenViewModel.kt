package com.example.sevenwindsproject.presentation.viewmodels.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sevenwindsproject.network.location.LocationService
import com.example.sevenwindsproject.network.model.ShopLocationModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NearestRestaurantsScreenViewModel(
    private val locationService: LocationService
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        listOf<ShopLocationModel>()
    )
//eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJBdXRoZW50aWNhdGlvbiIsImlzcyI6ImNvZmZlZSBiYWNrZW5kIiwiaWQiOjIsImV4cCI6MTcwNTI2MjkwN30.WxDs3GZwheRy-hMoDMmREg0lRtRByEcskbxqzjF39OI
    fun getRestaurantsList(): List<ShopLocationModel> {
        viewModelScope.launch {
            locationService.locations()
                .onSuccess { list ->
                    Log.d("locationService", list.toString())
                    _uiState.update {
                        it
                    }
                }.onFailure {
                    Log.d("locationsService", it.toString())
                }
        }

        return _uiState.value
    }
}
