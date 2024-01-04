package com.example.alp_vp_dev1.repository

import android.util.Log
import com.example.alp_vp_dev1.model.User
import com.example.alp_vp_dev1.services.AuthServices
import java.net.HttpURLConnection

class AuthRepositories(private val authServices: AuthServices) {

    suspend fun login(
        email: String,
        password: String
    ): String {
        val result = authServices.login(email, password)
        if (result.status == HttpURLConnection.HTTP_OK) {
            println("Success: $result")
            return result.message
        }
        println("failed: $result")
        return result.message
    }

    suspend fun logout() {}

    suspend fun create_user() {}

}