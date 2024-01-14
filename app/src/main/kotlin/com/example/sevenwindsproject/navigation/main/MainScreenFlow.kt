package com.example.sevenwindsproject.navigation.main

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.sevenwindsproject.navigation.SevenWindsScreen
import com.example.sevenwindsproject.presentation.screens.GoogleMapScreen
import com.example.sevenwindsproject.presentation.screens.MenuScreen
import com.example.sevenwindsproject.presentation.screens.NearestRestaurantsScreen
import com.example.sevenwindsproject.presentation.screens.OrderScreen
import com.example.sevenwindsproject.presentation.viewmodels.main.MenuScreenViewModel
import com.example.sevenwindsproject.presentation.viewmodels.main.NearestRestaurantsScreenViewModel
import com.google.android.gms.maps.model.LatLng
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.mainScreenFlow(
    navController: NavHostController,
    screenName: (String) -> Unit,
    currentLocation: LatLng?,
    routeToPreviousScreen: (String) -> Unit,
    isIconButtonActive: (Boolean) -> Unit
) {
    navigation(
        startDestination = MainScreen.NearestScreen.route,
        route = SevenWindsScreen.MainScreenFlow.route
    ) {
        /**
         * Nearest restaurants screen
         */
        composable(
            route = MainScreen.NearestScreen.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            NearestRestaurantsScreen(
                viewModel = koinViewModel<NearestRestaurantsScreenViewModel>(),
                navHostController = navController
            )
            screenName("Ближайшие кофейни")
            routeToPreviousScreen(SevenWindsScreen.AuthScreenFlow.route)
            isIconButtonActive(true)
        }

        /**
         * Google map screen
         */
        composable(
            route = MainScreen.GoogleMapScreen.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            GoogleMapScreen(point = currentLocation ?: LatLng(0.0, 0.0))
            screenName("Карта")
            routeToPreviousScreen(MainScreen.NearestScreen.route)
            isIconButtonActive(true)
        }

        /**
         * Menu screen
         */
        composable(
            route = MainScreen.MenuScreen.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            MenuScreen(
                viewModel = koinViewModel<MenuScreenViewModel>(),
                navHostController = navController
            )
            screenName("Меню")
            routeToPreviousScreen(MainScreen.NearestScreen.route)
            isIconButtonActive(true)
        }

        /**
         * Order screen
         */
        composable(
            route = MainScreen.OrderScreen.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            OrderScreen(navController)
            screenName("Ваш заказ")
            routeToPreviousScreen(MainScreen.MenuScreen.route)
            isIconButtonActive(true)
        }
    }
}
