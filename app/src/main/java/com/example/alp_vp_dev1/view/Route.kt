package com.example.alp_vp_dev1.view

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

enum class ListScreen() {
    OfferRide,
    Login,
    InputDestination,
    History
}

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
            composable(ListScreen.InputDestination.name) {}
            composable(ListScreen.History.name) {}
            composable(ListScreen.OfferRide.name) {}
        }
    }
}