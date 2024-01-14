package com.example.sevenwindsproject.presentation.screens

import PrimaryButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sevenwindsproject.navigation.SevenWindsScreen
import com.example.sevenwindsproject.navigation.auth.AuthScreen
import com.example.sevenwindsproject.presentation.viewmodels.auth.RegistrationScreenViewModel
import com.example.sevenwindsproject.ui.components.fields.PasswordTextField
import com.example.sevenwindsproject.ui.components.fields.PrimaryTextField
import com.example.sevenwindsproject.ui.theme.LocalDimensions
import com.example.sevenwindsproject.ui.theme.PrimaryMain
import com.example.sevenwindsproject.ui.theme.SfUiDisplayNormal15
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    viewModel: RegistrationScreenViewModel,
    navHostController: NavHostController
) {
    val state by viewModel.mutableState.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    val dimensions = LocalDimensions.current

    if (state is RegistrationScreenViewModel.State.Success) {
        navHostController.navigate(SevenWindsScreen.MainScreenFlow.route) {
            popUpTo(0) {
                inclusive = true
                saveState = false
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = dimensions.horizontalPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PrimaryTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.login,
            title = "e-mail",
            description = "Введите почту",
            errorText =
            when (state) {
                is RegistrationScreenViewModel.State.Error -> RegistrationScreenViewModel.ERROR
                is RegistrationScreenViewModel.State.ValidationError -> {
                    (state as? RegistrationScreenViewModel.State.ValidationError)?.loginError
                }

                else -> null
            },
            isError = (state as? RegistrationScreenViewModel.State.ValidationError)?.loginError != null
                    || (state is RegistrationScreenViewModel.State.Error),
            onTextChange = { login ->
                viewModel.changeLogin(login)
            }
        )
        Spacer(modifier = Modifier.height(dimensions.standardSpacer))
        Column(verticalArrangement = Arrangement.spacedBy(dimensions.fieldSpacer)) {
            Text(
                text = "Пароль",
                style = SfUiDisplayNormal15.copy(color = PrimaryMain)
            )

            PasswordTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.password,
                placeholderText = "Введите пароль",
                errorText =
                when (state) {
                    is RegistrationScreenViewModel.State.Error -> RegistrationScreenViewModel.ERROR
                    is RegistrationScreenViewModel.State.ValidationError -> {
                        (state as? RegistrationScreenViewModel.State.ValidationError)?.passwordError
                    }

                    else -> null
                },
                isError = (state as? RegistrationScreenViewModel.State.ValidationError)?.passwordError != null
                        || (state is RegistrationScreenViewModel.State.Error),
                onTextChange = { password ->
                    viewModel.changePassword(password)
                }
            )
        }
        Spacer(modifier = Modifier.height(dimensions.standardSpacer))
        Column(verticalArrangement = Arrangement.spacedBy(dimensions.fieldSpacer)) {
            Text(
                text = "Повторите пароль",
                style = SfUiDisplayNormal15.copy(color = PrimaryMain)
            )

            PasswordTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.repeatedPassword,
                placeholderText = "Повторите пароль",
                errorText =
                when (state) {
                    is RegistrationScreenViewModel.State.Error -> RegistrationScreenViewModel.ERROR
                    is RegistrationScreenViewModel.State.ValidationError -> {
                        (state as? RegistrationScreenViewModel.State.ValidationError)?.passwordError
                    }

                    else -> null
                },
                isError = (state as? RegistrationScreenViewModel.State.ValidationError)?.repeatedPasswordError != null
                        || (state as? RegistrationScreenViewModel.State.ValidationError)?.passwordError != null
                        || (state is RegistrationScreenViewModel.State.Error),
                onTextChange = {repeatedPassword ->
                    viewModel.changeRepeatedPassword(repeatedPassword)
                }
            )
        }
        Spacer(modifier = Modifier.height(dimensions.standardSpacer))
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Регистрация",
            onButtonClick = {
                viewModel.request()
            }
        )
        Spacer(modifier = Modifier.height(dimensions.standardSpacer))
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Назад",
            onButtonClick = {
                navHostController.navigate(AuthScreen.LoginScreen.route) {
                    popUpTo(0) {
                        inclusive = true
                        saveState = false
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreen(
        navHostController = rememberNavController(),
        viewModel = koinViewModel<RegistrationScreenViewModel>()
    )
}
