package com.example.sevenwindsproject.network.model

import kotlinx.serialization.Serializable

/**
 * Class which contains info about product
 *
 * @param [id] product id
 * @param [name] product name
 * @param [imageURL] link to product image
 * @param [price] product price
 */
@Serializable
data class MenuModel(
    val id: Int,
    val name: String,
    val imageURL: String,
    val price: Int
)
