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
import com.example.sevenwindsproject.presentation.viewmodels.auth.LoginScreenViewModel
import com.example.sevenwindsproject.ui.components.fields.PasswordTextField
import com.example.sevenwindsproject.ui.components.fields.PrimaryTextField
import com.example.sevenwindsproject.ui.theme.LocalDimensions
import com.example.sevenwindsproject.ui.theme.PrimaryMain
import com.example.sevenwindsproject.ui.theme.SfUiDisplayNormal15
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginScreenViewModel,
    navHostController: NavHostController
) {
    val dimensions = LocalDimensions.current
    val state by viewModel.mutableState.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    if (state is LoginScreenViewModel.State.Success) {
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
            title = "e-mail",
            value = uiState.login,
            description = "Введите почту",
            onTextChange = { login ->
                viewModel.changeLogin(login)
            },
            errorText =
            when (state) {
                is LoginScreenViewModel.State.Error -> LoginScreenViewModel.LOGIN_ERROR
                is LoginScreenViewModel.State.ValidationError -> {
                    (state as? LoginScreenViewModel.State.ValidationError)?.loginError
                }

                else -> null
            },
            isError = (state as? LoginScreenViewModel.State.ValidationError)?.loginError != null ||
                    (state is LoginScreenViewModel.State.Error)
        )
        Spacer(modifier = Modifier.height(dimensions.standardSpacer))
        Column(verticalArrangement = Arrangement.spacedBy(dimensions.fieldSpacer)) {
            Text(
                text = "Пароль",
                style = SfUiDisplayNormal15.copy(color = PrimaryMain)
            )

            PasswordTextField(
                modifier = Modifier.fillMaxWidth(),
                placeholderText = "Введите пароль",
                value = uiState.password,
                errorText =
                when (state) {
                    is LoginScreenViewModel.State.Error -> LoginScreenViewModel.PASSWORD_ERROR
                    is LoginScreenViewModel.State.ValidationError -> {
                        (state as? LoginScreenViewModel.State.ValidationError)?.passwordError
                    }

                    else -> null
                },
                isError = (state as? LoginScreenViewModel.State.ValidationError)?.passwordError != null,
                onTextChange = { password ->
                    viewModel.changePassword(password)
                }
            )
        }
        Spacer(modifier = Modifier.height(dimensions.standardSpacer))
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Вход",
            onButtonClick = {
                viewModel.request()
            }
        )
        Spacer(modifier = Modifier.height(dimensions.standardSpacer))
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Регестрация",
            onButtonClick = {
                navHostController.navigate(AuthScreen.RegistrationScreen.route) {
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
fun SignUpScreenPreview() {
    SignUpScreen(
        viewModel = koinViewModel(),
        navHostController = rememberNavController()
    )
}
