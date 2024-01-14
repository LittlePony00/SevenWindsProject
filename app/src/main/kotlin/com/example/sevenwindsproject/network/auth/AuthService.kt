package com.example.sevenwindsproject.network.auth

import com.example.sevenwindsproject.network.model.LoginModel
import com.example.sevenwindsproject.network.model.TokenModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
   suspend fun login(@Body model: LoginModel): Result<TokenModel>

    @POST("auth/register")
    suspend fun register(@Body model: LoginModel): Result<TokenModel>
}

class RetrofitAuthService(
    private val dispatcher: CoroutineDispatcher
) : AuthService {

    private val baseUrl = "http://147.78.66.203:3210"
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override suspend fun login(model: LoginModel): Result<TokenModel> = withContext(dispatcher) {
        try {
            retrofit.create(AuthService::class.java).login(model)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(model: LoginModel): Result<TokenModel> = withContext(dispatcher) {
        try {
            retrofit.create(AuthService::class.java).register(model)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
