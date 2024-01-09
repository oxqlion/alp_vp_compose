package com.example.alp_vp_dev1.viewmodel

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.alp_vp_dev1.model.OfferRide
import com.example.alp_vp_dev1.model.PlacesAutocomplete
import com.example.alp_vp_dev1.model.RideModel
import com.example.alp_vp_dev1.repository.RideContainer
import com.example.alp_vp_dev1.view.ListScreen
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class OfferRideViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(OfferRide())
    val uiState: StateFlow<OfferRide> = _uiState.asStateFlow()

    val list = listOf("date", "date2")

    val standbyLocationAutoFill = mutableStateListOf<PlacesAutocomplete>()
    val destinationLocationAutoFill = mutableStateListOf<PlacesAutocomplete>()

    var standbyName: String? = null
    var destinationName: String? = null

    private var standbyLatLng: LatLng? = null
    private var destinationLatLng: LatLng? = null

    var carType: String? = null
    var carCapacity: String? = null
    var carLicensePlate: String? = null
    var notes: String? = null

    private var selectedDate: String? = null
    private var selectedTime: String? = null

    private var job: Job? = null

    fun searchStandbyPlace(query: String, context: Context) {
        val placesClient = Places.createClient(context)

        job?.cancel()
        standbyLocationAutoFill.clear()
        try {
            job = viewModelScope.launch {
                val request = FindAutocompletePredictionsRequest
                    .builder()
                    .setQuery(query)
                    .build()

                placesClient
                    .findAutocompletePredictions(request)
                    .addOnSuccessListener { response ->
                        standbyLocationAutoFill += response.autocompletePredictions.map {
                            standbyLocationAutoFill.forEach { place ->
                                println("masuk ke tempat tempat: ${place.address}")
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
                                println("masuk ke tempat tempat: ${place.address}")
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

    fun getStandbyCoordinates(result: PlacesAutocomplete, context: Context) {
        val placesClient = Places.createClient(context)

        val placeFields = listOf(Place.Field.LAT_LNG)
        val request = FetchPlaceRequest.newInstance(result.placeId, placeFields)

        placesClient.fetchPlace(request)
            .addOnSuccessListener {
                if (it != null) {
                    standbyLatLng = it.place.latLng!!
                    println("it standby latlng : ${it.place.latLng}")
                    println("curr standby lat lng : $standbyLatLng")
                } else {
                    println("di get coor success listener gaada place jir")
                }
            }
            .addOnFailureListener {
                it.printStackTrace()
            }
    }

    fun getDestinationCoordinates(result: PlacesAutocomplete, context: Context) {
        val placesClient = Places.createClient(context)

        val placeFields = listOf(Place.Field.LAT_LNG)
        val request = FetchPlaceRequest.newInstance(result.placeId, placeFields)

        placesClient.fetchPlace(request)
            .addOnSuccessListener {
                if (it != null) {
                    destinationLatLng = it.place.latLng!!
                    println("it dest latlng : ${it.place.latLng}")
                    println("curr dest lat lng : $destinationLatLng")
                } else {
                    println("di get coor success listener gaada place jir")
                }
            }
            .addOnFailureListener {
                it.printStackTrace()
            }
    }

    fun createRide(user_id: Int, navController: NavController) {
        println("masuk create ride view model")
        if (standbyName != null && destinationName != null && standbyLatLng != null && destinationLatLng != null) {

            println("create ride viewmodel coordinates found")
            println("standby name : $standbyName")
            println("destination name : $destinationName")
            println("standby latlng : $standbyLatLng")
            println("destination latlng : $destinationLatLng")

            val newRide = RideModel(
                user_id = user_id,  // Assuming you have a function to get the current user's ID
                ride_status = 0,  // Assuming you have a constant for pending status
                start_location = standbyName ?: "",
                destination_location = destinationName ?: "",
                start_lat = standbyLatLng?.latitude ?: 0.0,
                start_lng = standbyLatLng?.longitude ?: 0.0,
                destination_lat = destinationLatLng?.latitude ?: 0.0,
                destination_lng = destinationLatLng?.longitude ?: 0.0,
                going_date = selectedDate ?: "",
                going_time = selectedTime ?: "",
                car_model = carType ?: "",
                car_license_plate = carLicensePlate ?: "",
                car_capacity = carCapacity ?: "",
                notes = notes ?: ""
            )

            viewModelScope.launch {
                println("calling api with data : $newRide")
                val offeredRide = RideContainer().rideRepositories.createRide(newRide)
                println("new ride created with response : $offeredRide, trying to redirect ...")
                if (offeredRide.contains("200")) {
                    println("response status 200. redirecting ...")
                    navController.navigate(ListScreen.Home.name)
                } else {
                    println("response status create ride is not 200")
                }
            }
        } else {
            println("create ride viewmodel coordinates not found!")
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AddressField(
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

    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DateTime() {
        val calendarState = rememberSheetState()

        CalendarDialog(
            state = calendarState,
            config = CalendarConfig(
                monthSelection = true,
                yearSelection = true,
                style = CalendarStyle.MONTH,
                disabledDates = listOf(LocalDate.now().plusDays(7))
            ),
            selection = CalendarSelection.Date { date ->
                val formatter = DateTimeFormatter.ofPattern("d MMMM YYYY")
                val formattedDate = date.format(formatter)
                selectedDate = formattedDate
                println("SelectedDate: $date")
            }
        )

        val clockState = rememberSheetState()
        ClockDialog(
            state = clockState,
            config = ClockConfig(
                is24HourFormat = true
            ),
            selection = ClockSelection.HoursMinutes { hours, minutes ->
                println("SelectedTime: $hours:$minutes")
                selectedTime = "$hours:$minutes"
            }
        )

        Column(
            modifier = Modifier
                .padding(24.dp, 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Button(
                    onClick = { calendarState.show() },
                    colors = ButtonDefaults.buttonColors(Color(0xFFD0FF00))
                ) {
                    Text(
                        text = "Date",
                        color = Color.Black
                    )
                }

                Button(
                    onClick = { clockState.show() },
                    colors = ButtonDefaults.buttonColors(Color(0xFFD0FF00))
                ) {
                    Text(
                        text = "Time",
                        color = Color.Black
                    )
                }
            }
        }
    }
}