package com.example.alp_vp_dev1.view

//noinspection UsingMaterialAndMaterial3Libraries
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.alp_vp_dev1.model.RideModel
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.CameraPositionState


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RideDetailsView(
    ride: RideModel,
    navController: NavController
) {

    val singapore = LatLng(1.35, 103.87)

    val standby = LatLng(ride.start_lat, ride.start_lng)
    val destination = LatLng(ride.destination_lat, ride.destination_lng)

    val standbyState = rememberMarkerState(
        position = standby
    )
    val destinationState = rememberMarkerState(
        position = destination
    )

    val boundsBuilder = LatLngBounds.Builder()
    boundsBuilder.include(standbyState.position)
    boundsBuilder.include(destinationState.position)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(standby, 15f)
    }

    val markerState = rememberMarkerState(
        position = singapore
    )


//    val sheetState = rememberModalBottomSheetState()
//    val scope = rememberCoroutineScope()
//    var showBottomSheet by remember { mutableStateOf(true) }

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(
            skipPartiallyExpanded = false,
            density = LocalDensity.current
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = standbyState
            )
            Marker(
                state = destinationState
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .shadow(24.dp, shape = RoundedCornerShape(0.dp))
                .clip(shape = RoundedCornerShape(0.dp))
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = { navController.navigate(ListScreen.Home.name) },
                modifier = Modifier
                    .padding(24.dp)
                    .size(36.dp)
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
            IconButton(
                onClick = {  },
                modifier = Modifier
                    .padding(24.dp)
                    .size(36.dp)
            ) {
                Icon(Icons.Filled.HelpOutline, contentDescription = "Help")
            }
        }

        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetContainerColor = Color.White,
            sheetShadowElevation = 24.dp,
            sheetPeekHeight = 170.dp,
            sheetContent = {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(24.dp, 0.dp, 24.dp, 100.dp)
                ) {
                    Text(
                        text = "Ride Details",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 20.dp)
                            .fillMaxWidth()
                    )
                    BottomSheetRideStatus(1)
                    DriverDetails(
                        driverName = "Tri Winarno",
                        rating = 4.7f,
                        vehicleDetails = ride.car_model,
                        licensePlate = "L 8888 AB"
                    )

                    PassengerDetails(passengers = 3, capacity = 7)

                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier
                            .padding(0.dp, 24.dp)
                    )

                    RideDetails(
                        date = ride.going_date,
                        time = ride.going_time,
                        standbyAddress = ride.start_location,
                        destinationAddress = ride.destination_location
                    )

                }
            }) {
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            Button(
                onClick = { },
                modifier = Modifier
                    .padding(16.dp)
                    .height(48.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD0FF00)
                )
            ) {
                Text(
                    text = "Join Ride",
                    fontSize = 24.sp,
                    color = Color.Black
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RideDetails(
    date: String,
    time: String,
    standbyAddress: String,
    destinationAddress: String
) {

    val dateFormat = DateTimeFormatter.ofPattern("d MMMM yyyy")
    val formattedDate = LocalDate.now().format(dateFormat)

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .background(Color.Black, shape = RoundedCornerShape(12.dp)),
        ) {
            Text(
                text = if (date == formattedDate) {
                    "Today"
                } else {
                    date
                },
                color = Color.White,
                modifier = Modifier
                    .padding(24.dp, 12.dp, 0.dp, 12.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = ", $time",
                color = Color.White,
                modifier = Modifier
                    .padding(0.dp, 12.dp, 24.dp, 12.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }

        Row(
            Modifier
                .padding(0.dp, 16.dp, 0.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Filled.MyLocation,
                "Standby Point",
                Modifier
                    .size(42.dp)
                    .padding(0.dp, 0.dp, 16.dp, 0.dp)
            )
            Column {
                Text(
                    text = standbyAddress,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 0.dp, 6.dp)
                )
                Text(
                    text = "Standby Point",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black.copy(alpha = 0.5f)
                )
            }
        }

        Row(
            Modifier
                .padding(0.dp, 16.dp, 0.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Filled.LocationOn,
                "Destination Point",
                Modifier
                    .size(42.dp)
                    .padding(0.dp, 0.dp, 16.dp, 0.dp)
            )
            Column {
                Text(
                    text = destinationAddress,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 0.dp, 6.dp)
                )
                Text(
                    text = "Destination Point",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@Composable
fun PassengerDetails(
    passengers: Int,
    capacity: Int
) {
    Row(
        modifier = Modifier
            .padding(0.dp, 16.dp, 0.dp, 0.dp)
            .fillMaxWidth()
            .background(Color(0xFFF4F4F4), shape = RoundedCornerShape(12.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Filled.People,
            "Passenger",
            Modifier
                .padding(24.dp)
                .size(40.dp)
        )
        Text(
            text = "$passengers / $capacity Passengers",
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .padding(24.dp)
        )
    }
}

@Composable
fun DriverDetails(
    driverName: String,
    rating: Float,
    vehicleDetails: String,
    licensePlate: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 24.dp, 0.dp, 0.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Filled.AccountCircle,
                "Driver",
                modifier = Modifier
                    .size(72.dp)
                    .padding(0.dp, 0.dp, 12.dp, 0.dp)
            )

            Column {
                Text(
                    text = driverName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Row {
                    Icon(
                        Icons.Filled.Star,
                        "Rating",
                        tint = Color(0xFFFFA800),
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Text(
                        text = rating.toString(),
                        fontSize = 20.sp
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .width(150.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = vehicleDetails,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.End,
                fontSize = 20.sp
            )
            Text(
                text = licensePlate,
                textAlign = TextAlign.End,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun BottomSheetRideStatus(status: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFADD7E8)
            )
        ) {
            Text(
                text = "Standby",
                color = if (status == 1) Color.Black else Color.Black.copy(alpha = 0.5f),
                fontWeight = if (status == 1) FontWeight.Bold else FontWeight.Normal
            )
        }
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFA7A6)
            )
        ) {
            Text(
                text = "Ongoing",
                color = if (status == 2) Color.Black else Color.Black.copy(alpha = 0.5f),
                fontWeight = if (status == 2) FontWeight.Bold else FontWeight.Normal
            )
        }
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF99F5AE)
            )
        ) {
            Text(
                text = "Finished",
                color = if (status == 3) Color.Black else Color.Black.copy(alpha = 0.5f),
                fontWeight = if (status == 3) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RideDetailsPreview() {
//    RideDetailsView()
}