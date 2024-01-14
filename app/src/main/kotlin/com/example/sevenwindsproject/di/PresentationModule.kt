package com.example.sevenwindsproject.di

import com.example.sevenwindsproject.presentation.viewmodels.auth.LoginScreenViewModel
import com.example.sevenwindsproject.presentation.viewmodels.auth.RegistrationScreenViewModel
import com.example.sevenwindsproject.presentation.viewmodels.main.MenuScreenViewModel
import com.example.sevenwindsproject.presentation.viewmodels.main.NearestRestaurantsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val providesPresentationModule = module {

    viewModel {
        LoginScreenViewModel(
            authService = get()
        )
    }

    viewModel {
        RegistrationScreenViewModel(
            authService = get()
        )
    }

    viewModel {
        NearestRestaurantsScreenViewModel(
            locationService = get()
        )
    }

    viewModel {
        MenuScreenViewModel(
            locationService = get()
        )
    }
}
