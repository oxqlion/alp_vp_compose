package com.example.alp_vp_dev1.services

import com.example.alp_vp_dev1.model.APIResponse
import com.example.alp_vp_dev1.model.RideDetailsModel
import com.example.alp_vp_dev1.model.RideModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RideServices {

    @GET("ride")
    suspend fun rides(): List<RideModel>

    @GET("ride/{rideId}")
    suspend fun getRideDetails(@Path("rideId") rideId: Int): RideDetailsModel

    @POST("ride")
    suspend fun createRide(@Body ride: RideModel): APIResponse

    @GET("ride_user")
    suspend fun ride_user(@Query("userId") userId: Int): List<RideModel>
}