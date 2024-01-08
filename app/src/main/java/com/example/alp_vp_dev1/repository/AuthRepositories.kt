package com.example.alp_vp_dev1.repository

import com.example.alp_vp_dev1.model.User
import com.example.alp_vp_dev1.services.AuthServices
import java.net.HttpURLConnection

class AuthRepositories(private val authServices: AuthServices) {

    suspend fun login(user: User): String {
        return try {
            val result = authServices.login(user)

            if (result.status == HttpURLConnection.HTTP_OK) {
                println("Success: $result")
                println("${result::class.simpleName}")
                return "{ $result }" // Return relevant data from the successful response
            } else {
                println("Failed: $result")
                throw Exception("Login failed: ${result.message}") // Re-throw with a more informative message
            }
        } catch (e: Exception) {
            // Handle specific exceptions or provide a generic message
            println("Error during login: ${e.message}")
            "Login failed due to an error" // Return a generic error message
        }
    }


    suspend fun logout() {}

    suspend fun create_user(user: User): String {
        println("masuk create user repo")
        return try {
            val result = authServices.create_user(user)
            println("masuk create user repo after declare val result")
            if (result.status == HttpURLConnection.HTTP_OK) {
                println("mantap status 200")
                return result.status.toString()
            } else {
                println("register gagal")
                throw Exception("Register failed: ${result.status}")
            }
        } catch (e: Exception) {
            println("Error during create user: ${e.message}")
            "Register failed due to an error"
        }
    }

    suspend fun check_user(userId: Int): String {
        return try {
            val result = authServices.check_user(userId)

            if (result.status == HttpURLConnection.HTTP_OK) {
                println("Success: $result")
                println("${result::class.simpleName}")
                return "{ $result }" // Return relevant data from the successful response
            } else {
                println("Failed: $result")
                throw Exception("Login failed: ${result.message}") // Re-throw with a more informative message
            }
        } catch (e: Exception) {
            // Handle specific exceptions or provide a generic message
            println("Error during login: ${e.message}")
            "Login failed due to an error" // Return a generic error message
        }
    }

}