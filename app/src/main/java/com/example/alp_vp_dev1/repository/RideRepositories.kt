package com.example.alp_vp_dev1.repository

import com.example.alp_vp_dev1.model.HistoryModel
import com.example.alp_vp_dev1.model.PassengerUserRide
import com.example.alp_vp_dev1.model.RideDetailsModel
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
                return "${result.status}, ${result.ride_id}"
            } else {
                println("create ride repositories else gagal")
                throw Exception("create ride failed: ${result.message}")
            }
        } catch (e: Exception) {
            println("create ride repositories catch: ${e.message}")
            "create ride repositories error : ${e.message}"
        }
    }

    suspend fun rides(): List<RideModel> {
        println("masuk rides repository")
        try {
            println("masuk rides repository try")
            val ridesList = rideServices.rides()
            if (ridesList.isEmpty()) println("rideList di repo empty")
            val data = mutableListOf<RideModel>()

            for (ride in ridesList) {
                val newRide = RideModel(
                    status = ride.status,
                    message = ride.message,
                    ride_id = ride.ride_id,
                    driver_id = ride.driver_id,
                    ride_status = ride.ride_status,
                    start_location = ride.start_location,
                    destination_location = ride.destination_location,
                    start_lat = ride.start_lat.toDouble(),
                    start_lng = ride.start_lng.toDouble(),
                    destination_lat = ride.destination_lat.toDouble(),
                    destination_lng = ride.destination_lng.toDouble(),
                    going_date = ride.going_date,
                    going_time = ride.going_time,
                    car_model = ride.car_model,
                    car_capacity = ride.car_capacity
                )
                for (entry in data) {
                    println("entry in repo data : $ride")
                }
                data.add(newRide)
            }
            return data
        } catch (e: Exception) {
            println("rides repository error : ${e.message}")
            return mutableListOf()
        }
    }

    suspend fun userRides(userId: Int): List<HistoryModel> {
        println("masuk userrides repository")
        try {
            println("masuk userrides repository try user id : $userId")
            val ridesList = rideServices.userRides(userId)
            if (ridesList.isEmpty()) {
                println("user rideList di repo empty : $ridesList")
                throw Exception("user rideList di repo empty : $ridesList")
            }
            val data = mutableListOf<HistoryModel>()

            for (ride in ridesList) {
                val newRide = HistoryModel(
                    driver_status = ride.driver_status,
                    passanger_id = ride.passanger_id,
                    passenger_destination_address = ride.passenger_destination_address,
                    passenger_destination_lat = ride.passenger_destination_lat,
                    passenger_destination_lng = ride.passenger_destination_lng,
                    passenger_pickup_address = ride.passenger_pickup_address,
                    passenger_pickup_lat = ride.passenger_pickup_lat,
                    passenger_pickup_lng = ride.passenger_pickup_lng,
                    passenger_status = ride.passenger_status,
                    price = ride.price,
                    promo_id = ride.promo_id,
                    review = ride.review ?: "No review yet",
                    ride_id = ride.ride_id,

                    // Missing parameters (replace placeholders with actual values):
                    car_capacity = ride.car_capacity, // Example placeholder
                    car_license_plate = ride.car_license_plate, // Example placeholder
                    car_model = ride.car_model, // Example placeholder
                    destination_lat = ride.passenger_destination_lat, // Assuming same as passenger destination
                    destination_lng = ride.passenger_destination_lng, // Assuming same as passenger destination
                    destination_location = ride.passenger_destination_address, // Assuming same as passenger destination
                    driver_id = ride.driver_id, // Example placeholder
                    going_date = ride.going_date, // Example placeholder
                    going_time = ride.going_time, // Example placeholder
                    notes = "", // Example placeholder
                    ride_status = ride.ride_status, // Example placeholder
                    start_lat = ride.passenger_pickup_lat, // Assuming same as passenger pickup
                    start_lng = ride.passenger_pickup_lng, // Assuming same as passenger pickup
                    start_location = ride.passenger_pickup_address, // Assuming same as passenger pickup
                    ur_id = 456 // Example placeholder
                )
                for (entry in data) {
                    println("entry in repo data : $ride")
                }
                data.add(newRide)
            }
            return data
        } catch (e: Exception) {
            println("rides repository error : ${e.message}")
            return mutableListOf()
        }
    }

    suspend fun getRideDetails(rideId: Int): RideDetailsModel {
        println("masuk get ride details repo")
        try {
            val result = rideServices.getRideDetails(rideId)
            if (result.status != 200) {
                println("error making request. response : ${result.message}")
            }
            println("making new ride model")
            val newRide = RideDetailsModel(
                status = result.status,
                message = result.message,
                ride_id = result.ride_id,
                driver_id = result.driver_id,
                driver_name = result.driver_name,
                ride_status = result.ride_status,
                start_location = result.start_location,
                passengers = result.passengers,
                destination_location = result.destination_location,
                start_lat = result.start_lat.toDouble(),
                start_lng = result.start_lng.toDouble(),
                destination_lat = result.destination_lat.toDouble(),
                destination_lng = result.destination_lng.toDouble(),
                going_date = result.going_date,
                going_time = result.going_time,
                car_model = result.car_model,
                car_license_plate = result.car_license_plate,
                car_capacity = result.car_capacity,
                notes = result.notes
            )

            println("new ride model created : $newRide")

            return newRide
        } catch (e: Exception) {
            println("error get ride details repo : $e")
            throw e
        }
    }

    suspend fun joinRide(rideId: Int): String {
        println("coba get ride id di join ride repo")
        try {
            val result = rideServices.joinRide(rideId)
            if (result.status != 200) {
                println("error making request to get ride id in repo. response : ${result.message}")
                throw Exception("get ride id in repo status not 200 : ${result.message}")
            } else {
                return result.ride_id.toString()
            }
        } catch (e: Exception) {
            "error ga dapet ride id : ${e.message}"
            throw e
        }
    }

    suspend fun createUserRide(userRide: PassengerUserRide): String {
        var response: String? = null
        try {
            println("masuk ke try crate user ride repo")
            val result = rideServices.createUserRide(userRide)
            response = result.status.toString()

            if (result.status == HttpURLConnection.HTTP_OK) {
                println("response status create user ride repo 200")
                return result.status.toString()
            } else {
                println("reponse status create user ride repo bukan 200: ${result.message}")
                throw Exception("create ride failed: ${result.message}")
            }
        } catch (e: Exception) {
            "exception error in create user ride repository : ${e.message}"
            throw e
        }
    }
}