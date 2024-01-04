package com.example.alp_vp_dev1.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alp_vp_dev1.viewmodel.PassengerRideDetailsUIState
import com.example.alp_vp_dev1.viewmodel.PassengerRideDetailsViewModel

enum class ListScreen() {
    OfferRide,
    Login,
    InputDestination,
    History,
    RideDetails
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RideShareRoute() {
    val navController = rememberNavController()

    Scaffold {
        NavHost(
            navController = navController,
            startDestination = ListScreen.InputDestination.name,
        ) {
            composable(ListScreen.Login.name) {}
            composable(ListScreen.InputDestination.name) {
                InputDestinationView(
                    navigate = { navController.navigate(ListScreen.RideDetails.name) }
                )
            }
            composable(ListScreen.History.name) {}
            composable(ListScreen.OfferRide.name) {}
            composable(ListScreen.RideDetails.name) {
//                val passengerRideDetailsViewModel: PassengerRideDetailsViewModel = viewModel()
//                passengerRideDetailsViewModel.rideDetailView()

//                when (passengerRideDetailsViewModel.passengerRideDetailUIState) {
//                    is PassengerRideDetailsUIState.Success -> {
                RideDetailsView()
//                    }

//                    is PassengerRideDetailsUIState.Error -> {}
//                    is PassengerRideDetailsUIState.Loading -> {}
//                }

            }
        }
    }
}