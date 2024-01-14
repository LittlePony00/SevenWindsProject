package com.example.sevenwindsproject.presentation.viewmodels.main

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.LocationServices

class GoogleMapScreenViewModel(
    private val context: Context
) : ViewModel() {

    private val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    fun getLocation() {

    }
}