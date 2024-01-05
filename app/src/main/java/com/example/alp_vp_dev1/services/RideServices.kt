package com.example.alp_vp_dev1.services

import com.example.alp_vp_dev1.model.RideModel
import retrofit2.http.GET

interface RideServices {

    @GET("ride")
    suspend fun ride(): RideModel

}