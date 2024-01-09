package com.example.alp_vp_dev1.model

data class PassengerUserRide(
    val status: Int = -1,
    val message: String = "",
    val ur_id: Int = -1,
    val ride_id: String,
    val passanger_id: String,
    val driver_status: String,
    val passenger_status: String,

    val passenger_pickup_address: String,
    val passenger_destination_address: String,

    val passenger_pickup_lat: Double,
    val passenger_pickup_lng: Double,

    val passenger_destination_lat: Double,
    val passenger_destination_lng: Double,

    val price: String,
    val promo_id: Any,
    val review: Any,
)