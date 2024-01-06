package com.example.alp_vp_dev1.repository

import com.example.alp_vp_dev1.model.RideModel
import com.example.alp_vp_dev1.services.AuthServices
import com.example.alp_vp_dev1.services.RideServices
import java.net.HttpURLConnection

class RideRepositories(private val rideServices: RideServices) {

    suspend fun createRide(ride: RideModel): String {
        println("masuk create ride repositories")
        return try {
            val result = rideServices.createRide(ride)

            if (result.status == HttpURLConnection.HTTP_OK) {
                println("mantap status create ride repositories 200")
                return result.status.toString()
            } else {
                println("create ride repositories else gagal")
                throw Exception("create ride failed: ${result.message}")
            }
        } catch (e: Exception) {
            println("create ride repositories catch: ${e.message}")
            "create ride repositories error : ${e.message}"
        }
    }

}