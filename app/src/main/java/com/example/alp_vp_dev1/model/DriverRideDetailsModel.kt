package com.example.alp_vp_dev1.model

data class DriverRideDetailsModel(
    val ride_id: Int,
    val passanger_id: Int,
    val passenger_status: String,
    val driver_status: String,
    val passenger_pickup_address: String, 
    val passenger_destination_address: String, 
    val passenger_pickup_lat: Double = 0.0,
    val passenger_pickup_lng: Double = 0.0,
    val passenger_destination_lat: Double = 0.0,
    val passenger_destination_lng: Double = 0.0,
    val review: String,
    val promo_id: Int,
    val price: Int,
    val message: String = "",
    val status: Int = -1,
    val email: String,
    val password: String,
    val name: String = "",
    val phone: String = "",
    val driver: String = "",
)