package com.example.sevenwindsproject.presentation.viewmodels.auth

import android.util.Patterns
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sevenwindsproject.network.auth.AuthService
import com.example.sevenwindsproject.network.model.LoginModel
import com.example.sevenwindsproject.network.model.RegisterModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegistrationScreenViewModel(
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
            val passwordError: String?,
            val repeatedPasswordError: String?
        ) : State()
    }

    private val _uiState = MutableStateFlow(
        RegisterModel(
            login = String(),
            password = String(),
            repeatedPassword = String()
        )
    )

    val uiState = _uiState.asStateFlow()

    var mutableState = MutableStateFlow<State>(State.Init)
        private set

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

    fun changeRepeatedPassword(repeatedPassword: String) {
        _uiState.update {
            uiState.value.copy(repeatedPassword = repeatedPassword)
        }
    }

    fun request() {
        val login = validateLogin(login = uiState.value.login)
        val password = validatePassword(password = uiState.value.password)
        val repeatedPassword = validateRepeatedPassword(
            password = uiState.value.password,
            repeatedPassword = uiState.value.repeatedPassword
        )

        if (login != null ||
            password != null ||
            repeatedPassword != null) {
            mutableState.update {
                State.ValidationError(
                    loginError = login,
                    passwordError = password,
                    repeatedPasswordError = repeatedPassword
                )
            }
        } else {
            viewModelScope.launch {
                mutableState.update {
                    State.Loading
                }

                authService.register(
                    LoginModel(
                        login = uiState.value.login,
                        password = uiState.value.password
                    )
                ).onSuccess {
                    mutableState.update {
                        State.Success
                    }
                }.onFailure {
                    mutableState.update {
                        State.Error
                    }
                }
            }
        }
    }

    private fun validateLogin(login: String): String? {
        return if (login.isBlank()) "Поле почты не должно быть пустым"
        else if (!Patterns.EMAIL_ADDRESS.matcher(login).matches()) "Поле не соответствует ни одному e-mail"
        else null
    }

    private fun validatePassword(password: String): String? {
        return if (password.isBlank()) "Поле пароля не должно быть пустым" else null
    }

    private fun validateRepeatedPassword(password: String, repeatedPassword: String): String? {
        return if (password != repeatedPassword) "Пароли не сопадают" else null
    }

    companion object {
        const val ERROR = "Что-то пошло не так!"
    }
}
