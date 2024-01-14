package com.example.sevenwindsproject.network.model

import kotlinx.serialization.Serializable

/**
 * Class which contains info about login and password
 *
 * @param [login] - user login
 * @param [password] - user password
 */
@Serializable
data class LoginModel(
    val login: String,
    val password: String
)
