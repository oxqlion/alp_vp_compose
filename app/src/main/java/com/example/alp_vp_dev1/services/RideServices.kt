package com.example.alp_vp_dev1.services

import com.example.alp_vp_dev1.model.APIResponse
import com.example.alp_vp_dev1.model.RideModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RideServices {

    @GET("ride")
    suspend fun ride(): RideModel

    @POST("ride")
    suspend fun createRide(@Body ride: RideModel): APIResponse
}