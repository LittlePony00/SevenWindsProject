package com.example.sevenwindsproject.navigation

import androidx.compose.runtime.Immutable

@Immutable
sealed class SevenWindsScreen(val route: String) {

    /**
     * Screens for authentications
     */
    data object AuthScreenFlow: SevenWindsScreen("Auth")

    /**
     * Main screens for user
     */
    data object MainScreenFlow: SevenWindsScreen("Main")
}
