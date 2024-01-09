package com.example.alp_vp_dev1.model

data class RideDetailsModel(
    val car_capacity: String,
    val car_license_plate: String,
    val car_model: String,
    val destination_lat: Double,
    val destination_lng: Double,
    val destination_location: String,
    val driver_id: Int,
    val driver_name: String,
    val going_date: String,
    val going_time: String,
    val message: String,
    val notes: String,
    val passengers: Int,
    val ride_id: Int,
    val ride_status: String,
    val start_lat: Double,
    val start_lng: Double,
    val start_location: String,
    val status: Int
)