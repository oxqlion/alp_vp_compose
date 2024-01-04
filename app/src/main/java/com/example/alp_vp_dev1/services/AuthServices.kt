package com.example.alp_vp_dev1.services

import com.example.alp_vp_dev1.model.APIResponse
import com.example.alp_vp_dev1.model.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

interface AuthServices {

    @POST("login")
    suspend fun login(@Body user: User): User

    @DELETE("logout")
    suspend fun logout(): APIResponse

    @POST("create_user")
    suspend fun create_user(@Body user: User): APIResponse

}