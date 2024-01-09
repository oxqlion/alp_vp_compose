package com.example.alp_vp_dev1.viewmodel

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.outlined.Discount
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.alp_vp_dev1.model.InputDestination
import com.example.alp_vp_dev1.model.PassengerUserRide
import com.example.alp_vp_dev1.model.PlacesAutocomplete
import com.example.alp_vp_dev1.model.RideModel
import com.example.alp_vp_dev1.repository.RideContainer
import com.example.alp_vp_dev1.view.ListScreen
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

sealed interface InputDestinationUIState {
    data class Success(val data: Int) : InputDestinationUIState
    object Error : InputDestinationUIState
    object Loading : InputDestinationUIState
}

class InputDestinationViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(InputDestination())
    val uiState: StateFlow<InputDestination> = _uiState.asStateFlow()

    var inputDestinationUIState: InputDestinationUIState by mutableStateOf(InputDestinationUIState.Loading)
        private set

    private var data by Delegates.notNull<Int>()

    var capacity: Int? = 0
    var userId: Int? = 0;

    val pickUpLocationAutoFill = mutableStateListOf<PlacesAutocomplete>()
    val destinationLocationAutoFill = mutableStateListOf<PlacesAutocomplete>()

    var pickUpName: String? = null
    var destinationName: String? = null

    private var pickUpLatLng: LatLng? = null
    private var destinationLatLng: LatLng? = null

    var rideIdnya: Int? = null

    private var job: Job? = null


    fun joinRideId(rideId: Int) {
        viewModelScope.launch {
            try {
                println("masuk ke joinrideid input destinatoin viewmodel")
                data = RideContainer().rideRepositories.joinRide(rideId).toInt()
                println("dapet data ride id buat join ride di viewmodel : $data")
                rideIdnya = data.toInt()
                inputDestinationUIState = InputDestinationUIState.Success(data)
            } catch (e: Exception) {
                println("ga dapet ride id di viewmodel error : ${e.message}")
                inputDestinationUIState = InputDestinationUIState.Error
            }
        }
    }

    fun insertPassengerUserRide(
        navController: NavController
    ) {
        println("masuk ke insert passenger user ride viewmodel")
        try {
            if (pickUpName != null && destinationName != null && pickUpLatLng != null && destinationLatLng != null) {
                val newUr = pickUpName?.let {
                    PassengerUserRide(
                        ride_id = rideIdnya.toString(),
                        passanger_id = userId.toString(),
                        passenger_status = "0",
                        driver_status = "0",
                        passenger_pickup_address = it,
                        passenger_pickup_lat = pickUpLatLng!!.latitude,
                        passenger_pickup_lng = pickUpLatLng!!.longitude,
                        passenger_destination_address = destinationName!!,
                        passenger_destination_lat = destinationLatLng!!.latitude,
                        passenger_destination_lng = destinationLatLng!!.longitude,
                        review = "",
                        promo_id = 1,
                        price = "12000"
                    )
                }

                println("coba launch viewmodelscope di inputdes viewmodel")

                viewModelScope.launch {
                    println("coba insert new ur di viewmodel")
                    val insertUr = newUr?.let { RideContainer().rideRepositories.createUserRide(it) }
                    println("hasil : $insertUr")
                    if (insertUr != null) {
                        if (insertUr.contains("200")) {
                            println("status 200 berhasil. redirecting ...")
                            navController.navigate(ListScreen.Home.name)
                        } else {
                            println("status di viewmodel scope insert new ur di viewmodel bukan 200")
                        }
                    } else {
                        println("insert ur gaada")
                    }
                }

            } else {
                println("input destination viewmodel data masih null")
            }
        } catch (e: Exception) {
            println("exception insert user ride di viewmodel")
        }
    }

    fun searchPickupPlace(query: String, context: Context) {
        val placesClient = Places.createClient(context)

        job?.cancel()
        pickUpLocationAutoFill.clear()
        try {
            job = viewModelScope.launch {
                val request = FindAutocompletePredictionsRequest
                    .builder()
                    .setQuery(query)
                    .build()

                placesClient
                    .findAutocompletePredictions(request)
                    .addOnSuccessListener { response ->
                        pickUpLocationAutoFill += response.autocompletePredictions.map {
                            pickUpLocationAutoFill.forEach { place ->
                                println("passenger masuk ke tempat tempat: ${place.address}")
                            }
                            PlacesAutocomplete(
                                it.getFullText(null).toString(),
                                it.placeId
                            )
                        }
                    }
                    .addOnFailureListener {
                        it.printStackTrace()
                        println("it cause : ${it.cause}")
                        println("it message: ${it.message}")
                    }
            }
        } catch (e: Exception) {
            println("error exception places : $e")
        }
    }

    fun getPickUpPlaceDetails(result: PlacesAutocomplete, context: Context) {
        val placesClient = Places.createClient(context)

        val placeFields = listOf(Place.Field.LAT_LNG)
        val request = FetchPlaceRequest.newInstance(result.placeId, placeFields)

        placesClient.fetchPlace(request)
            .addOnSuccessListener {
                if (it != null) {
                    pickUpLatLng = it.place.latLng!!
                    println("passenger it standby latlng : ${it.place.latLng}")
                    println("passenger curr standby lat lng : $pickUpLatLng")
                } else {
                    println("passenger di get coor success listener gaada place jir")
                }
            }
            .addOnFailureListener {
                it.printStackTrace()
            }
    }

    fun searchDestinationPlace(query: String, context: Context) {
        val placesClient = Places.createClient(context)

        job?.cancel()
        destinationLocationAutoFill.clear()
        try {
            job = viewModelScope.launch {
                val request = FindAutocompletePredictionsRequest
                    .builder()
                    .setQuery(query)
                    .build()

                placesClient
                    .findAutocompletePredictions(request)
                    .addOnSuccessListener { response ->
                        destinationLocationAutoFill += response.autocompletePredictions.map {
                            destinationLocationAutoFill.forEach { place ->
                                println("passenger masuk ke tempat tempat: ${place.address}")
                            }
                            PlacesAutocomplete(
                                it.getFullText(null).toString(),
                                it.placeId
                            )
                        }
                    }
                    .addOnFailureListener {
                        it.printStackTrace()
                        println("it cause : ${it.cause}")
                        println("it message: ${it.message}")
                    }
            }
        } catch (e: Exception) {
            println("error exception places : $e")
        }
    }

    fun getDestinationPlaceDetails(result: PlacesAutocomplete, context: Context) {
        val placesClient = Places.createClient(context)

        val placeFields = listOf(Place.Field.LAT_LNG)
        val request = FetchPlaceRequest.newInstance(result.placeId, placeFields)

        placesClient.fetchPlace(request)
            .addOnSuccessListener {
                if (it != null) {
                    destinationLatLng = it.place.latLng!!
                    println("passenger it standby latlng : ${it.place.latLng}")
                    println("passenger curr standby lat lng : $destinationLatLng")
                } else {
                    println("passenger di get coor success listener gaada place jir")
                }
            }
            .addOnFailureListener {
                it.printStackTrace()
            }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TextField(
        value: String,
        onValueChanged: (String) -> Unit,
        text: String,
        keyboardOptions: KeyboardOptions,
        modifier: Modifier = Modifier
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChanged,
            label = { Text(text = text) },
            keyboardOptions = keyboardOptions,
            modifier = modifier,
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFD0FF00),
                unfocusedBorderColor = Color(0xFFD0FF00)
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = "Location"
                )
            }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun NumField(
        value: String,
        onValueChanged: (String) -> Unit,
        text: String,
        keyboardOptions: KeyboardOptions,
        modifier: Modifier = Modifier
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChanged,
            label = { Text(text = text) },
            keyboardOptions = keyboardOptions,
            modifier = modifier,
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFD0FF00),
                unfocusedBorderColor = Color(0xFFD0FF00)
            )
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PromoField(
        value: String,
        onValueChanged: (String) -> Unit,
        text: String,
        keyboardOptions: KeyboardOptions,
        modifier: Modifier = Modifier
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChanged,
            label = { Text(text = text) },
            keyboardOptions = keyboardOptions,
            modifier = modifier,
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFD0FF00),
                unfocusedBorderColor = Color(0xFFD0FF00)
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Discount,
                    contentDescription = "Promo"
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        imageVector = Icons.Filled.NavigateNext,
                        contentDescription = "Detail Promo"
                    )
                }
            }
        )
    }

    @Composable
    fun TopShadow(alpha: Float = 1f, height: Dp = 8.dp) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = alpha)
                        )
                    )
                )
        )
    }
}