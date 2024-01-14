package com.example.sevenwindsproject.presentation.viewmodels.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sevenwindsproject.network.location.LocationService
import com.example.sevenwindsproject.network.model.MenuModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MenuScreenViewModel(
    private val locationService: LocationService
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        listOf<MenuModel>()
    )

    fun getMenuList(): List<MenuModel> {
        viewModelScope.launch {
            locationService.menu(1)
                .onSuccess { result ->
                    _uiState.update {
                        result
                    }
                }.onFailure {
                    Log.d("localService", it.toString())
                }
        }

        return _uiState.value
    }
}