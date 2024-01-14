package com.example.sevenwindsproject.network.model

import kotlinx.serialization.Serializable

/**
 * Class which contains info about user token
 *
 * @param [token] user token
 * @param [tokenLifeTime] contains info about toke life cycle
 */
@Serializable
data class TokenModel(
    val token: String,
    val tokenLifeTime: Int
)
