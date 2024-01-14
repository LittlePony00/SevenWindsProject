package com.example.sevenwindsproject.network.location

import com.example.sevenwindsproject.network.model.MenuModel
import com.example.sevenwindsproject.network.model.ShopLocationModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationService {

    @GET("locations")
    suspend fun locations(): Result<List<ShopLocationModel>>

    @GET("location/{id}/menu")
    suspend fun menu(@Path("id") id: Int): Result<List<MenuModel>>
}

class RetrofitLocationService(
    private val dispatcher: CoroutineDispatcher
) : LocationService {

    private val baseUrl = "http://147.78.66.203:3210"
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override suspend fun locations(): Result<List<ShopLocationModel>> = withContext(dispatcher) {
        try {
            retrofit.create(LocationService::class.java).locations()
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun menu(id: Int): Result<List<MenuModel>> = withContext(dispatcher) {
        try {
            retrofit.create(LocationService::class.java).menu(id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
