package com.example.alp_vp_dev1.view

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardBackspace
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.alp_vp_dev1.model.User
import com.example.alp_vp_dev1.viewmodel.InputDestinationViewModel

@Composable
fun InputDestinationView(
    user: User,
    inputDestinationViewModel: InputDestinationViewModel,
    navController: NavController,
    context: Context,
    rideId: Int
) {

    val inputDestinationUiState by inputDestinationViewModel.uiState.collectAsState()

    var pickUp by remember {
        mutableStateOf("")
    }

    var destination by remember {
        mutableStateOf("")
    }

    var capacity by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .background(Color(0xFFD0FF00))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, top = 25.dp, end = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardBackspace,
                contentDescription = "Back",
                modifier = Modifier
                    .clickable {
                        navController.popBackStack()
                    }
                    .size(28.dp)
            )

            Text(
                text = "Ride Destination",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp
            )
        }

        Text(
            modifier = Modifier
                .padding(top = 5.dp, end = 25.dp)
                .fillMaxWidth(),
            text = "Jl. Mayjend. Joonosewojo No.2",
            textAlign = TextAlign.Right,
            fontSize = 18.sp
        )

        Column(
            modifier = Modifier
                .padding(top = 25.dp)
                .fillMaxSize()
                .background(Color(0XFFFFFFFF), RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp)),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Text(
                    modifier = Modifier
                        .padding(start = 24.dp, top = 24.dp)
                        .fillMaxWidth(),
                    text = "Input Pick Up Point",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                inputDestinationViewModel.TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 8.dp),
                    value = pickUp,
                    onValueChanged = {
                        inputDestinationViewModel.searchPickupPlace(it, context)
                        pickUp = it
                    },
                    text = "Your destination point ...",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )

                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .heightIn(max = 200.dp)
                ) {
                    items(inputDestinationViewModel.pickUpLocationAutoFill) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 6.dp)
                                .clickable {
                                    pickUp = it.address
                                    inputDestinationViewModel.pickUpName = it.address
                                    inputDestinationViewModel.getPickUpPlaceDetails(it, context)
                                    inputDestinationViewModel.pickUpLocationAutoFill.clear()
                                }
                        ) {
                            Text(
                                text = it.address,
                                modifier = Modifier
                                    .padding(12.dp)
                            )
                        }
                    }
                }

                Text(
                    modifier = Modifier
                        .padding(start = 24.dp, top = 24.dp)
                        .fillMaxWidth(),
                    text = "Input Destination Point",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                inputDestinationViewModel.TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 8.dp),
                    value = destination,
                    onValueChanged = {
                        inputDestinationViewModel.searchDestinationPlace(it, context)
                        destination = it
                    },
                    text = "Your destination point ...",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )

                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .heightIn(max = 200.dp)
                ) {
                    items(inputDestinationViewModel.destinationLocationAutoFill) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 6.dp)
                                .clickable {
                                    destination = it.address
                                    inputDestinationViewModel.destinationName = it.address
                                    inputDestinationViewModel.getDestinationPlaceDetails(
                                        it,
                                        context
                                    )
                                    inputDestinationViewModel.destinationLocationAutoFill.clear()
                                }
                        ) {
                            Text(
                                text = it.address,
                                modifier = Modifier
                                    .padding(12.dp)
                            )
                        }
                    }
                }

                Text(
                    modifier = Modifier
                        .padding(start = 24.dp, top = 24.dp)
                        .fillMaxWidth(),
                    text = "Total Passenger",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                inputDestinationViewModel.NumField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 8.dp),
                    value = capacity,
                    onValueChanged = {
                        capacity = it
                        inputDestinationViewModel.capacity = it.toInt()
                    },
                    text = "Capacity",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                )
            }

            Surface {
                Column(
                    modifier = Modifier
//                        .padding(top = 34.dp)
//                        .fillMaxSize()
                ) {
                    inputDestinationViewModel.TopShadow(alpha = .15f, height = 18.dp)

                    inputDestinationViewModel.PromoField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp, top = 18.dp, end = 24.dp, bottom = 8.dp),
                        value = inputDestinationUiState.promo,
                        onValueChanged = { inputDestinationUiState.promo = it },
                        text = "Use Promo Code",
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp, 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Total : ",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Rp 12.000",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Button(
                        modifier = Modifier
                            .padding(24.dp, 8.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(Color(0xFFD0FF00)),
                        onClick = {
                            println("button join ride di input destination di klik")

                            inputDestinationViewModel.rideIdnya = rideId
                            inputDestinationViewModel.userId = user.user_id

                            inputDestinationViewModel.insertPassengerUserRide(navController)

//                            navController.navigate(ListScreen.Home.name)
                        }
                    ) {
                        Text(
                            text = "Join Ride",
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InputDestinationPreview() {
//    InputDestinationView(navigate = {})
}