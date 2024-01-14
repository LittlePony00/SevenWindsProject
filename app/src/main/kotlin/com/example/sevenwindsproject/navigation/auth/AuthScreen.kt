package com.example.sevenwindsproject.navigation.auth

import androidx.compose.runtime.Immutable

@Immutable
sealed class AuthScreen(val route: String) {

    /**
     * Login screen
     */
    data object LoginScreen: AuthScreen("Login")

    /**
     * Registration screen
     */
    data object RegistrationScreen: AuthScreen("Registration")
}
