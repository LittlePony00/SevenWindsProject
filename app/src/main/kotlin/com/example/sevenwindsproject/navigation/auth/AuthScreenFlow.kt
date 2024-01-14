package com.example.sevenwindsproject.navigation.auth

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.sevenwindsproject.navigation.SevenWindsScreen
import com.example.sevenwindsproject.presentation.screens.RegistrationScreen
import com.example.sevenwindsproject.presentation.screens.SignUpScreen
import com.example.sevenwindsproject.presentation.viewmodels.auth.LoginScreenViewModel
import com.example.sevenwindsproject.presentation.viewmodels.auth.RegistrationScreenViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.authScreenFlow(
    navController: NavHostController,
    screenName: (String) -> Unit,
    isIconButtonActive: (Boolean) -> Unit
) {
    navigation(
        startDestination = AuthScreen.LoginScreen.route,
        route = SevenWindsScreen.AuthScreenFlow.route
    ) {
        /**
         * Login screen
         */
        composable(
            route = AuthScreen.LoginScreen.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            SignUpScreen(
                viewModel = koinViewModel<LoginScreenViewModel>(),
                 navHostController = navController
            )
            screenName("Вход")
            isIconButtonActive(false)
        }

        /**
         * Registration screen
         */
        composable(
            route = AuthScreen.RegistrationScreen.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            RegistrationScreen(
                viewModel = koinViewModel<RegistrationScreenViewModel>(),
                navHostController = navController
            )
            screenName("Регистрация")
            isIconButtonActive(false)
        }
    }
}
