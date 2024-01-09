package com.example.alp_vp_dev1.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Badge
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.alp_vp_dev1.R
import com.example.alp_vp_dev1.model.RideModel
import com.example.alp_vp_dev1.model.User
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

//val inter = FontFamily(
//    Font(R.font.inter_variable, FontWeight.Normal),
//    Font(R.font.inter_variable_italic, FontWeight.Normal)
//)

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeView(
    user: User,
    rides: List<RideModel>,
    navController: NavController
) {

    val pages = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = "History",
            selectedIcon = Icons.Filled.DirectionsCar,
            unselectedIcon = Icons.Outlined.DirectionsCar,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = "Profile",
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle,
            hasNews = false,
        )
    )

    val selectedItemByIndex by rememberSaveable {
        mutableStateOf(0)
    }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                modifier = Modifier
                    .shadow(48.dp)
                    .clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = {
                        navController.navigate(ListScreen.Home.name)
                    },
                    icon = {
                        BadgedBox(
                            badge = {  }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = "Home"
                            )
                        }
                    },
                    label = {
                        Text(text = "Home")
                    },
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate(ListScreen.History.name)
                    },
                    icon = {
                        BadgedBox(
                            badge = { }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.DirectionsCar,
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
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .width(250.dp),
                        text = "Where are you going today ?",
                        lineHeight = 36.sp,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.ExtraLight
                    )
                    Icon(
                        Icons.AutoMirrored.Filled.Help,
                        "Help",
                        Modifier.size(36.dp)
                    )
                }

                val dateFormat = DateTimeFormatter.ofPattern("d MMMM yyyy")
                val formattedDate = LocalDate.now().format(dateFormat)

                if (user.driver.contains("1")) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .heightIn(max = 500.dp, min = 500.dp)
                    ) {
                        items(rides) { ride ->

                            var newDate: String? = null

                            newDate = if (ride.going_date == formattedDate) {
                                "Today"
                            } else {
                                ride.going_date
                            }

                            val dateTime: String = "$newDate, ${ride.going_time}"

                            HomeCard(
                                ride_id = ride.ride_id.toString(),
                                dateTime = dateTime,
                                standby = ride.start_location,
                                destination = ride.destination_location,
                                status = ride.ride_status.toString(),
                                navController = navController
                            )
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .heightIn(max = 600.dp, min = 600.dp)
                    ) {
                        items(rides) { ride ->

                            var newDate: String? = null

                            newDate = if (ride.going_date == formattedDate) {
                                "Today"
                            } else {
                                ride.going_date
                            }

                            val dateTime: String = "$newDate, ${ride.going_time}"

                            HomeCard(
                                ride_id = ride.ride_id.toString(),
                                dateTime = dateTime,
                                standby = ride.start_location,
                                destination = ride.destination_location,
                                status = ride.ride_status.toString(),
                                navController = navController
                            )
                        }
                    }
                }
            }

            if (user.driver.contains("1")) {

                Button(
                    modifier = Modifier
                        .padding(20.dp, 20.dp, 20.dp, 110.dp)
                        .height(60.dp)
                        .fillMaxWidth(),
                    onClick = {
                        navController.navigate(ListScreen.OfferRide.name)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD0FF00)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Offer Ride",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(Modifier.width(8.dp))  // Add horizontal spacing
                        Icon(
                            imageVector = Icons.Filled.ArrowForward,
                            contentDescription = "Offer Ride Button",
                            tint = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HomeCard(
    ride_id: String,
    dateTime: String,
    standby: String,
    destination: String,
    status: String,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .shadow(10.dp, shape = RoundedCornerShape(5.dp)),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = dateTime,
//                    fontFamily = inter,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .absolutePadding(top = 5.dp, left = 10.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.card_car),
                        contentDescription = "Mobil",
                        modifier = Modifier
                            .size(80.dp)
                            .padding(top = 16.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Fit
                    )
                }
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .absolutePadding(top = 10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.panah),
                        contentDescription = "tujuan",
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(18.dp, 110.dp)
                            .graphicsLayer(scaleY = 1f),
                        contentScale = ContentScale.Crop
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 12.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = standby,
                            lineHeight = 15.sp,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier
                                .padding(0.dp)
                        )
                        Text(
                            text = "Standby point",
                            lineHeight = 11.sp,
                            fontSize = 10.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 0.dp)
                        )
                    }
                    Column(
                        modifier = Modifier.padding(top = 24.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = destination,
                            lineHeight = 15.sp,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier
                                .padding(0.dp)
                        )
                        Text(
                            text = "Destination",
                            lineHeight = 11.sp,
                            fontSize = 10.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 0.dp)
                        )
                    }
                }
            }

            Divider(
                modifier = Modifier
                    .heightIn(2.dp)
                    .padding(horizontal = 16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (status.contains("0")) {
                    Card(
                        modifier = Modifier
                            .background(color = Color(0xFFADD7E8), shape = RoundedCornerShape(8.dp))
                            .padding(16.dp, 5.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFADD7E8)
                        )
                    ) {
                        Text(
                            text = "Standby",
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Black
                        )
                    }
                } else if (status.contains("1")) {
                    Card(
                        modifier = Modifier
                            .background(color = Color(0xFFFFA7A6), shape = RoundedCornerShape(8.dp))
                            .padding(16.dp, 5.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFA7A6)
                        )
                    ) {
                        Text(
                            text = "Ongoing",
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Black
                        )
                    }
                } else if (status.contains("2")) {
                    Card(
                        modifier = Modifier
                            .background(color = Color(0xFF99F5AE), shape = RoundedCornerShape(8.dp))
                            .padding(16.dp, 5.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF99F5AE)
                        )
                    ) {
                        Text(
                            text = "Finished",
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Black
                        )
                    }
                }


                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD0FF00)
                    ),
                    onClick = { navController.navigate(ListScreen.RideDetails.name + "/" + ride_id) }
                ) {
                    Text(
                        text = "Details",
                        color = Color.Black,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = false, showBackground = false)
@Composable
fun HomePreview() {
//    HomeView()
//    card()
}
