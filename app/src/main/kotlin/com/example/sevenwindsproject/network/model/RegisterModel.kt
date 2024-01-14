package com.example.sevenwindsproject.network.model

import kotlinx.serialization.Serializable

/**
 * Class which contains info about login, password and repeated password
 *
 * @param [login] - user login
 * @param [password] - user password
 * @param [repeatePassword] - user repeated password
 */
@Serializable
data class RegisterModel(
    val login: String,
    val password: String,
    val repeatedPassword: String
)
