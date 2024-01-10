package com.example.alp_vp_dev1.model

data class PassengerUserRide(
    val status: Int = -1,
    val message: String = "-1",
    val ur_id: Int = -1,
    val ride_id: String = "-1",
    val passanger_id: String = "-1",
    val driver_status: String = "0",
    val passenger_status: String = "0",

    val passenger_pickup_address: String = "-1",
    val passenger_destination_address: String = "-1",

    val passenger_pickup_lat: Double = 0.1,
    val passenger_pickup_lng: Double = 0.1,

    val passenger_destination_lat: Double = 0.1,
    val passenger_destination_lng: Double = 0.1,

    val price: String = "1",
    val promo_id: Any = "1",
    val review: Any = "-1",
)