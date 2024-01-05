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
import com.example.alp_vp_dev1.model.User
import com.example.alp_vp_dev1.viewmodel.CheckUserViewModel
import com.example.alp_vp_dev1.viewmodel.InputDestinationViewModel
import com.example.alp_vp_dev1.viewmodel.LoginViewModel
import com.example.alp_vp_dev1.viewmodel.PassengerRideDetailsUIState
import com.example.alp_vp_dev1.viewmodel.PassengerRideDetailsViewModel
import com.example.alp_vp_dev1.viewmodel.RegisterViewModel
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
    RideDetails
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun RideShareRoute() {
    val navController = rememberNavController()
    val dataStore = DataStoreManager(LocalContext.current)

    Scaffold {
        NavHost(
            navController = navController,
            startDestination = ListScreen.Splash.name,
        ) {
            composable(ListScreen.Splash.name) {

                println("masuk ke splash route")
                SplashScreenView()

                val checkUserViewModel: CheckUserViewModel = viewModel()

//                checkUserViewModel.viewModelScope.launch {
//                    dataStore.clearDataStore()
//                }
                checkUserViewModel.viewModelScope.launch {
                    val userDataFlow: Flow<User?> = dataStore.getUser
                    userDataFlow.collect() {
                        if (it == null) {
                            println("gaada user bro")
                            navController.navigate(ListScreen.Login.name)
                        } else {
                            println("ada user bjir: $it")
                            println("user data type: ${it::class.simpleName}")
                            navController.navigate(ListScreen.InputDestination.name)
                        }
                    }
                }
            }
            composable(ListScreen.Home.name) {
                println("masuk ke home route")
                HomeView()
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

                if (loggedInUserObj != null && loggedInUserObj.driver.contains("1")) {
                    InputDestinationView(
                        loggedInUserObj,
                        inputDestinationViewModel,
                        navigate = { navController.navigate(ListScreen.RideDetails.name) }
                    )
                }
            }
            composable(ListScreen.History.name) {}
            composable(ListScreen.OfferRide.name) {}
            composable(ListScreen.RideDetails.name) {
                RideDetailsView()
            }
        }
    }
}