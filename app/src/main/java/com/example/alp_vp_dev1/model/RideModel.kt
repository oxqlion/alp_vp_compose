package com.example.alp_vp_dev1.model

import com.google.android.gms.maps.model.LatLng

data class RideModel(
    val status: Int = -1,
    val message: String = "",
    val ride_id: Int = -1,
    val driver_id: Int = -1,
    val ride_status: Int = -1,
    val start_location: String = "",
    val destination_location: String = "",
    val start_lat: Double = 0.0,
    val start_lng: Double = 0.0,
    val destination_lat: Double = 0.0,
    val destination_lng: Double = 0.0,
    val going_date: String = "",
    val going_time: String = "",
    val car_model: String = "",
    val car_capacity: String = ""
)