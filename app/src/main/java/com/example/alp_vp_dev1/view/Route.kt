package com.example.alp_vp_dev1.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alp_vp_dev1.data.DataStoreManager
import com.example.alp_vp_dev1.data.PropertyLoader
import com.example.alp_vp_dev1.model.User
import com.example.alp_vp_dev1.repository.AuthContainer
import com.example.alp_vp_dev1.repository.AuthRepositories
import com.example.alp_vp_dev1.viewmodel.CheckUserViewModel
import com.example.alp_vp_dev1.viewmodel.HomeUIState
import com.example.alp_vp_dev1.viewmodel.HomeViewModel
import com.example.alp_vp_dev1.viewmodel.InputDestinationViewModel
import com.example.alp_vp_dev1.viewmodel.LoginViewModel
import com.example.alp_vp_dev1.viewmodel.OfferRideViewModel
import com.example.alp_vp_dev1.viewmodel.PassengerRideDetailsUIState
import com.example.alp_vp_dev1.viewmodel.PassengerRideDetailsViewModel
import com.example.alp_vp_dev1.viewmodel.ProfileViewModel
import com.example.alp_vp_dev1.viewmodel.RegisterViewModel
import com.example.alp_vp_dev1.viewmodel.RideDetailsUIState
import com.example.alp_vp_dev1.viewmodel.RideDetailsViewModel
import com.google.android.libraries.places.api.Places
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

enum class ListScreen() {
    Splash,
    Home,
    OfferRide,
    Login,
    Register,
    InputDestination,
    History,
    RideDetails,
    Profile
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun RideShareRoute() {
    val navController = rememberNavController()
    val dataStore = DataStoreManager(LocalContext.current)
    val context = LocalContext.current
    Places.initialize(context, MAPS_API_KEY)


    Scaffold {
        NavHost(
            navController = navController,
            startDestination = ListScreen.Splash.name,
        ) {
            composable(ListScreen.Splash.name) {

                println("masuk ke splash route")
                SplashScreenView()

                val checkUserViewModel: CheckUserViewModel = viewModel()

//                val userExist: Int = checkUserViewModel.checkUser(dataStore, navController)

                if (checkUserViewModel.checkUser(dataStore, navController) == 1) {
                    println("user exist")
                    navController.navigate(ListScreen.Home.name)
                } else {
                    println("there is no user exist in splash route")
                    navController.navigate(ListScreen.Login.name)
                }

//                checkUserViewModel.viewModelScope.launch {
//                    val userDataFlow: Flow<User?> = dataStore.getUser
//                    userDataFlow.collect() {
//                        if (it == null) {
//                            println("gaada user bro")
//                            navController.navigate(ListScreen.Login.name)
//                        } else {
//                            println("ada user bjir: $it")
//                            println("user data type: ${it::class.simpleName}")
//                            navController.navigate(ListScreen.InputDestination.name)
//                        }
//                    }
//                }
            }
            composable(ListScreen.Home.name) {
                println("masuk ke home route")

                val homeViewModel: HomeViewModel = viewModel()

                var loggedInUser: User? = null

                homeViewModel.viewModelScope.launch {
                    loggedInUser = dataStore.getUser.first()!!
                    if (loggedInUser == null) navController.navigate(ListScreen.Login.name)
                }

                println("logged in user ga null di home route")

                val loggedInUserObj = loggedInUser?.let { it1 ->
                    User(
                        user_id = it1.user_id,
                        email = loggedInUser!!.email,
                        password = "",
                        name = loggedInUser!!.name,
                        phone = loggedInUser!!.phone,
                        driver = loggedInUser!!.driver
                    )
                }

                if (loggedInUserObj != null) {
                    println("coba akses homeuistate")
                    when (homeViewModel.homeUIState) {
                        is HomeUIState.Success -> {
                            println("homeuistate dapet success")
                            HomeView(
                                loggedInUserObj,
                                (homeViewModel.homeUIState as HomeUIState.Success).data,
                                navController
                            )
                        }

                        is HomeUIState.Loading -> {
                            println("homeuistate masih loading...")
                        }
                        is HomeUIState.Error -> {
                            println("homeuistate error..")
                        }
                    }
                } else {
                    navController.navigate(ListScreen.Register.name)
                }
            }
            composable(ListScreen.Login.name) {
                val loginViewModel: LoginViewModel = viewModel()
                LoginView(loginViewModel, navController, dataStore)
            }
            composable(ListScreen.Register.name) {
                val registerViewModel: RegisterViewModel = viewModel()
                RegisterView(registerViewModel = registerViewModel, navController, dataStore)
            }
            composable(ListScreen.InputDestination.name) {
                val inputDestinationViewModel: InputDestinationViewModel = viewModel()

                var loggedInUser: User? = null

                inputDestinationViewModel.viewModelScope.launch {
                    loggedInUser = dataStore.getUser.first()!!
                    if (loggedInUser == null) navController.navigate(ListScreen.Login.name)
                }

                val loggedInUserObj = loggedInUser?.let { it1 ->
                    User(
                        user_id = it1.user_id,
                        email = loggedInUser!!.email,
                        password = "",
                        name = loggedInUser!!.name,
                        phone = loggedInUser!!.phone,
                        driver = loggedInUser!!.driver
                    )
                }

                if (loggedInUserObj != null) {
                    InputDestinationView(
                        loggedInUserObj,
                        inputDestinationViewModel,
                        navigate = { navController.navigate(ListScreen.RideDetails.name) }
                    )
                } else {
                    navController.navigate(ListScreen.Home.name)
                }
            }
            composable(ListScreen.History.name) {

            }
            composable(ListScreen.OfferRide.name) {
                val offerRideDetailsViewModel: OfferRideViewModel = viewModel()

                var loggedInUser: User? = null

                offerRideDetailsViewModel.viewModelScope.launch {
                    loggedInUser = dataStore.getUser.first()!!
                    if (loggedInUser == null) navController.navigate(ListScreen.Login.name)
                }

                val loggedInUserObj = loggedInUser?.let { it1 ->
                    User(
                        user_id = it1.user_id,
                        email = loggedInUser!!.email,
                        password = "",
                        name = loggedInUser!!.name,
                        phone = loggedInUser!!.phone,
                        driver = loggedInUser!!.driver
                    )
                }

                if (loggedInUserObj != null && loggedInUserObj.driver.contains("1")) {
                    OfferRideView(
                        loggedInUserObj,
                        offerRideDetailsViewModel,
                        context,
                        navController
                    )
                } else {
                    navController.navigate(ListScreen.Home.name)
                }
            }
            composable(ListScreen.RideDetails.name + "/{rideId}") {
                val rideDetailsViewModel: RideDetailsViewModel = viewModel()
                rideDetailsViewModel.loadRideDetails(
                    it.arguments?.getString("rideId")!!.toInt()
                )

                var loggedInUser: User? = null

                rideDetailsViewModel.viewModelScope.launch {
                    loggedInUser = dataStore.getUser.first()!!
                    if (loggedInUser == null) navController.navigate(ListScreen.Login.name)
                }

                val loggedInUserObj = loggedInUser?.let { it1 ->
                    User(
                        user_id = it1.user_id,
                        email = loggedInUser!!.email,
                        password = "",
                        name = loggedInUser!!.name,
                        phone = loggedInUser!!.phone,
                        driver = loggedInUser!!.driver
                    )
                }

                if (loggedInUserObj != null) {

                    when (rideDetailsViewModel.rideDetailsUIState) {
                        is RideDetailsUIState.Success -> {
                            RideDetailsView(
                                (rideDetailsViewModel.rideDetailsUIState as RideDetailsUIState.Success).data,
                                navController
                            )
                        }

                        is RideDetailsUIState.Error -> {}
                        is RideDetailsUIState.Loading -> {}
                    }
                } else {
                    navController.navigate(ListScreen.Home.name)
                }
            }
            composable(ListScreen.Profile.name) {
                val profileViewModel: ProfileViewModel = viewModel()
                ProfileView(navController, dataStore, profileViewModel)
            }
        }
    }
}