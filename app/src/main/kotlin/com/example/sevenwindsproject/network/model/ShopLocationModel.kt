package com.example.sevenwindsproject.network.model

import kotlinx.serialization.Serializable

/**
 * Class which contains info about shop location
 *
 * @param [id] shop id
 * @param [name] shop name
 * @param [point] Pair which contains shop latitude and longitude
 */
@Serializable
data class ShopLocationModel(
    val id: Int,
    val name: String,
    val point: Pair<Float, Float>
)
