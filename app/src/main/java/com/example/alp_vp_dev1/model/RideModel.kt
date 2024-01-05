package com.example.alp_vp_dev1.model

data class RideModel(
    val car_model: String = "",
    val car_plate_number: String = "",
    val destination_location: String = "",
    val driver_id: Int = -1,
    val going_time: String = "",
    val ride_id: Int = -1,
    val start_location: String = "",
    val status: String = ""
)