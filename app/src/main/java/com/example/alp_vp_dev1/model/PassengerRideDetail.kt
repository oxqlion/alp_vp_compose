package com.example.alp_vp_dev1.model

data class PassengerRideDetail(
    val message: String = "",
    val status: Int = -1,

    val car_model: String = "",
    val car_plate_number: String = "",
    val destination_location: String = "",
    val driver_id: String = "",
    val going_time: String = "",
    val id: Int = -1,
    val start_location: String = "",
    val ride_status: String = ""
)