package com.example.alp_vp_dev1.repository

import com.example.alp_vp_dev1.services.AuthServices
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthContainer {

    companion object{
        val ACCESS_TOKEN = ""
    }

    private val BASE_URL = "https://bddf-182-253-199-42.ngrok-free.app/api/"

    private val client = OkHttpClient.Builder()
        .hostnameVerifier { _, _ -> true }
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    private val retrofitService: AuthServices by lazy {
        retrofit.create(AuthServices::class.java)
    }

    val authRepositories: AuthRepositories by lazy {
        AuthRepositories(retrofitService)
    }
}