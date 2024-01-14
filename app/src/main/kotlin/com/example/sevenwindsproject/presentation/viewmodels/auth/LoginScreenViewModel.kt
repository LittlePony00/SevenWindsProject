package com.example.sevenwindsproject.presentation.viewmodels.auth

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sevenwindsproject.network.auth.AuthService
import com.example.sevenwindsproject.network.model.LoginModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginScreenViewModel(
    private val authService: AuthService
) : ViewModel() {

    @Immutable
    sealed class State {
        data object Init : State()
        data object Success : State()
        data object Loading : State()
        data object Error : State()
        data class ValidationError(
            val loginError: String?,
            val passwordError: String?
        ) : State()
    }

    var mutableState = MutableStateFlow<State>(State.Init)
        private set

    private val _uiState = MutableStateFlow(
        LoginModel(
            login = String(),
            password = String()
        )
    )

    val uiState = _uiState.asStateFlow()

    fun changeLogin(login: String) {
        _uiState.update {
            uiState.value.copy(login = login)
        }
    }

    fun changePassword(password: String) {
        _uiState.update {
            uiState.value.copy(password = password)
        }
    }

    fun request() {
        val login = validateLogin(uiState.value.login)
        val password = validatePassword(uiState.value.password)

        if (login != null || password != null) {
            mutableState.value = State.ValidationError(login, password)
        } else {
            viewModelScope.launch {
                mutableState.value = State.Loading

                authService.login(_uiState.value)
                    .onSuccess { _ ->
                        mutableState.value = State.Success

                    }
                    .onFailure {
                        Log.d("authService", it.toString() + "Error")
                        mutableState.value = State.Error
                    }
            }
        }
    }

    private fun validateLogin(login: String): String? {
        return if (login.isBlank()) "Введите почту" else null
    }

    private fun validatePassword(password: String): String? {
        return if (password.isBlank()) "Введите пароль" else null
    }

    companion object {
        const val LOGIN_ERROR = "Такой логин уже существует"
        const val PASSWORD_ERROR = "Неправильный пароль"
    }
}
