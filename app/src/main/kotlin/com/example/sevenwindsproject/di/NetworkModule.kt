package com.example.sevenwindsproject.di

import com.example.sevenwindsproject.network.auth.AuthService
import com.example.sevenwindsproject.network.auth.RetrofitAuthService
import com.example.sevenwindsproject.network.location.LocationService
import com.example.sevenwindsproject.network.location.RetrofitLocationService
import org.koin.core.qualifier.named
import org.koin.dsl.module

val providesNetworkModule = module {
    single<AuthService> {
        RetrofitAuthService(
            dispatcher = get(named(DoDispatchers.IO.name))
        )
    }

    single<LocationService> {
        RetrofitLocationService(
            dispatcher = get(named(DoDispatchers.IO.name))
        )
    }
}
