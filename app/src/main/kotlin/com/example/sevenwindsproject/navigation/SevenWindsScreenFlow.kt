package com.example.sevenwindsproject.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.sevenwindsproject.navigation.auth.authScreenFlow
import com.example.sevenwindsproject.navigation.main.mainScreenFlow
import com.google.android.gms.maps.model.LatLng

@Composable
fun SevenWindsScreenFlow(
    paddingValues: PaddingValues,
    currentLocation: LatLng?,
    navHostController: NavHostController = rememberNavController(),
    toolBarName: (String) -> Unit,
    routeToPreviousScreen: (String) -> Unit,
    isIconButtonActive: (Boolean) -> Unit
) {
    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navHostController,
        startDestination = SevenWindsScreen.AuthScreenFlow.route
    ) {
        authScreenFlow(
            navController = navHostController,
            screenName = { toolBarName(it) },
            isIconButtonActive = { isIconButtonActive(it) }
        )

        mainScreenFlow(
            navController = navHostController,
            screenName = { toolBarName(it) },
            currentLocation = currentLocation,
            routeToPreviousScreen = { routeToPreviousScreen(it) },
            isIconButtonActive = { isIconButtonActive(it) }
        )
    }
}
