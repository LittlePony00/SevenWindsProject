package com.example.sevenwindsproject.navigation.main

import androidx.compose.runtime.Immutable

@Immutable
sealed class MainScreen(val route: String) {

    /**
     * Screen which showing the nearest restaurants
     */
    data object NearestScreen: MainScreen("Nearest")

    /**
     * Google map screen
     */
    data object GoogleMapScreen: MainScreen("GoogleMap")

    /**
     * Screen for showing user orders
     */
    data object OrderScreen: MainScreen("Order")

    /**
     * Screen for showing restaurant menu
     */
    data object MenuScreen: MainScreen("Menu")
}
