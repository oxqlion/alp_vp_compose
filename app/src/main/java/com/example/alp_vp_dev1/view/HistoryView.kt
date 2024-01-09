package com.example.alp_vp_dev1.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.alp_vp_dev1.R
import com.example.alp_vp_dev1.model.RideModel
import com.example.alp_vp_dev1.model.User
import com.example.alp_vp_dev1.viewmodel.HistoryViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HistoryView(
    user: User,
    historyViewModel: HistoryViewModel,
    rides: List<RideModel>,
    navController: NavController
){
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                modifier = Modifier
                    .shadow(48.dp)
                    .clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
            ) {
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate(ListScreen.Home.name)
                    },
                    icon = {
                        BadgedBox(
                            badge = {  }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Home,
                                contentDescription = "Home"
                            )
                        }
                    },
                    label = {
                        Text(text = "Home")
                    },
                )
                NavigationBarItem(
                    selected = true,
                    onClick = {
                        navController.navigate(ListScreen.History.name)
                    },
                    icon = {
                        BadgedBox(
                            badge = {  }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.DirectionsCar,
                                contentDescription = "History"
                            )
                        }
                    },
                    label = {
                        Text(text = "History")
                    },
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate(ListScreen.Profile.name)
                    },
                    icon = {
                        BadgedBox(
                            badge = {  }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.AccountCircle,
                                contentDescription = "Profile"
                            )
                        }
                    },
                    label = {
                        Text(text = "Profile")
                    },
                )
            }
        }
    ){
        Column{
            Row(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Your Ride History",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Icon(
                    imageVector = Icons.Outlined.HelpOutline,
                    contentDescription = "help"
                )
            }

            val dateFormat = DateTimeFormatter.ofPattern("d MMMM yyyy")
            val formattedDate = LocalDate.now().format(dateFormat)

            LazyVerticalGrid(columns = GridCells.Fixed(1)){
                items(rides){ ride ->
                    var newDate: String? = null

                    newDate = if (ride.going_date == formattedDate) {
                        "Today"
                    } else {
                        ride.going_date
                    }

                    val dateTime: String = "$newDate, ${ride.going_time}"

                    HistoryCard(
                        dateTime = dateTime,
                        standby = ride.start_location,
                        carPlate = ride.car_license_plate,
                        carType = ride.car_model,
                        destination = ride.destination_location,
                        status = ride.ride_status.toString(),
                        user = user.driver
                    )
                }
            }
        }
    }


    /*
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .height(100.dp),
        contentAlignment = Alignment.BottomCenter
    ){
        Column {
            historyViewModel.TopShadow(alpha = .15f, height = 18.dp)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp, 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Icon(
                        modifier = Modifier
                            .size(30.dp)
                            .fillMaxWidth()
                            .align(CenterHorizontally),
                        imageVector = Icons.Outlined.Home,
                        contentDescription = "Home Page"
                    )

                    Text(
                        text = "Home"
                    )
                }

                Column {
                    Icon(
                        modifier = Modifier
                            .size(30.dp)
                            .fillMaxWidth()
                            .align(CenterHorizontally),
                        imageVector = Icons.Outlined.History,
                        contentDescription = "History"
                    )

                    Text(
                        text = "History"
                    )
                }

                Column {
                    Icon(
                        modifier = Modifier
                            .size(30.dp)
                            .fillMaxWidth()
                            .align(CenterHorizontally),
                        imageVector = Icons.Outlined.AccountCircle,
                        contentDescription = "Account"
                    )

                    Text(
                        text = "Account"
                    )
                }
            }
        }
    }*/
}
@Composable
fun HistoryCard(
    dateTime: String,
    standby: String,
    carPlate: String,
    carType: String,
    destination: String,
    status: String,
    user: String,
) {
    ElevatedCard(
        modifier = Modifier.padding(18.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 18.dp
        ),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp, 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = dateTime,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                Column {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = carPlate,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        textAlign = TextAlign.End
                    )

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = carType,
                        textAlign = TextAlign.End
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
            ) {
                Image(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(50.dp)
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.baseline_directions_car_24),
                    contentDescription = "car"
                )

                Image(
                    modifier = Modifier
                        .size(30.dp, 120.dp)
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.group_63),
                    contentDescription = "arrow"
                )

                Column(
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .width(140.dp),
                        text = standby,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Standby Point",
                        fontWeight = FontWeight.Light,
                        fontSize = 11.sp
                    )

                    Text(
                        modifier = Modifier
                            .width(140.dp)
                            .padding(top = 18.dp),
                        text = destination,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Destination",
                        fontWeight = FontWeight.Light,
                        fontSize = 11.sp
                    )
                }
                if (user.contains("0")) {
                    if (status.contains("0")) {
                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .background(Color(0xFFADD7E8), RoundedCornerShape(4.dp))
                                .padding(horizontal = 10.dp)
                                .padding(vertical = 6.dp)
                                .align(Alignment.Bottom),
                            text = "Submitted",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Right
                        )
                    }
                    else if (status.contains("1")) {
                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .background(Color(0xFFFFA7A6), RoundedCornerShape(4.dp))
                                .padding(horizontal = 10.dp)
                                .padding(vertical = 6.dp)
                                .align(Alignment.Bottom),
                            text = "Accepted",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Right
                        )
                    }
                    else if (status.contains("2")) {
                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .background(Color(0xFFBBFAB0), RoundedCornerShape(4.dp))
                                .padding(horizontal = 10.dp)
                                .padding(vertical = 6.dp)
                                .align(Alignment.Bottom),
                            text = "Finished",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Right
                        )
                    }
                }
                else {
                    if (status.contains("0")) {
                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .background(Color(0xFFADD7E8), RoundedCornerShape(4.dp))
                                .padding(horizontal = 10.dp)
                                .padding(vertical = 6.dp)
                                .align(Alignment.Bottom),
                            text = "Standby",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Right
                        )
                    }
                    else if (status.contains("1")) {
                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .background(Color(0xFFFFA7A6), RoundedCornerShape(4.dp))
                                .padding(horizontal = 10.dp)
                                .padding(vertical = 6.dp)
                                .align(Alignment.Bottom),
                            text = "Active",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Right
                        )
                    }
                    else if (status.contains("2")) {
                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .background(Color(0xFFBBFAB0), RoundedCornerShape(4.dp))
                                .padding(horizontal = 10.dp)
                                .padding(vertical = 6.dp)
                                .align(Alignment.Bottom),
                            text = "Finished",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Right
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HistoryPreview(){
    //HistoryView()
}