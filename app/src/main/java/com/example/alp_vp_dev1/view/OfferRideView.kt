package com.example.alp_vp_dev1.view

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardBackspace
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alp_vp_dev1.viewmodel.OfferRideViewModel
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.alp_vp_dev1.model.RideModel
import com.example.alp_vp_dev1.model.User
import com.google.type.DateTime
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import org.jetbrains.annotations.Contract
import java.time.LocalDate
import java.time.Clock

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OfferRideView(
    user: User,
    offerRideViewModel: OfferRideViewModel,
    context: Context,
    navController: NavController
) {
    val offerRideUiState by offerRideViewModel.uiState.collectAsState()
    val textFieldSize by remember { mutableStateOf(Size.Zero) }

    var standby by remember {
        mutableStateOf("")
    }
    var destination by remember {
        mutableStateOf("")
    }
    var carType by remember {
        mutableStateOf("")
    }
    var carCapacity by remember {
        mutableStateOf("")
    }

    var carLicensePlate by remember {
        mutableStateOf("")
    }

    var notes by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD0FF00))
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .height(80.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(start = 25.dp),
                imageVector = Icons.Filled.KeyboardBackspace,
                contentDescription = "Back"
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 50.dp),
                text = "Offer Ride",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White, RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp)),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 24.dp, top = 28.dp)
                        .fillMaxWidth(),
                    text = "Standby Point",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                offerRideViewModel.AddressField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 8.dp),
                    value = standby,
                    onValueChanged = {
                        offerRideViewModel.searchStandbyPlace(it, context)
                        standby = it
                    },
                    text = "Your address point ...",
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
                    items(offerRideViewModel.standbyLocationAutoFill) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 6.dp)
                                .clickable {
                                    standby = it.address
                                    offerRideViewModel.standbyName = it.address
                                    offerRideViewModel.getStandbyCoordinates(it, context)
                                    offerRideViewModel.standbyLocationAutoFill.clear()
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
                        .padding(start = 18.dp, top = 24.dp)
                        .fillMaxWidth(),
                    text = "Destination",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                offerRideViewModel.AddressField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 8.dp),
                    value = destination,
                    onValueChanged = {
                        offerRideViewModel.searchDestinationPlace(it, context)
                        destination = it
                    },
                    text = "Your address point ...",
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
                    items(offerRideViewModel.destinationLocationAutoFill) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 6.dp)
                                .clickable {
                                    destination = it.address
                                    offerRideViewModel.destinationName = it.address
                                    offerRideViewModel.getDestinationCoordinates(it, context)
                                    offerRideViewModel.destinationLocationAutoFill.clear()
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
                        .padding(start = 18.dp, top = 24.dp)
                        .fillMaxWidth(),
                    text = "Leaving Time",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    offerRideViewModel.DateTime()
                }

                Text(
                    modifier = Modifier
                        .padding(start = 18.dp, top = 24.dp)
                        .fillMaxWidth(),
                    text = "Notes & Car Capacity",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 24.dp, top = 8.dp)
                    ) {
                        OutlinedTextField(
                            modifier = Modifier
                                .width(220.dp),
                            value = carType,
                            onValueChange = {
                                carType = it
                            },
                            label = { Text(text = "Car Type (Car Paint)") },
//                            trailingIcon = {
//                                Icon(
//                                    offerRideUiState.iconType,
//                                    contentDescription = "arrow",
//                                    modifier = Modifier
//                                        .clickable {
//                                            offerRideUiState.expandedType =
//                                                !offerRideUiState.expandedType
//                                        }
//                                )
//                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color(0xFFD0FF00),
                                unfocusedBorderColor = Color(0xFFD0FF00)
                            ),
                            shape = RoundedCornerShape(15.dp)
                        )

//                        DropdownMenu(
//                            expanded = offerRideUiState.expandedType,
//                            onDismissRequest = { offerRideUiState.expandedType = false },
//                            modifier = Modifier
//                                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
//                                .background(Color(0x33D0FF00))
//                        ) {
//                            offerRideViewModel.list.forEach {
//                                DropdownMenuItem(text = { Text(text = it) }, onClick = {
//                                    offerRideUiState.selectedType = it
//                                    offerRideUiState.expandedType = false
//                                }
//                                )
//                            }
//                        }
                    }

                    Column(
                        modifier = Modifier
                            .padding(end = 24.dp, top = 8.dp)
                    ) {
                        OutlinedTextField(
                            modifier = Modifier
                                .width(110.dp),
                            value = carCapacity,
                            onValueChange = { carCapacity = it },
                            label = { Text(text = "Capacity") },
//                            trailingIcon = {
//                                Icon(
//                                    offerRideUiState.iconCapacity,
//                                    contentDescription = "arrow",
//                                    modifier = Modifier
//                                        .clickable {
//                                            offerRideUiState.expandedCapacity =
//                                                !offerRideUiState.expandedCapacity
//                                        }
//                                )
//                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color(0xFFD0FF00),
                                unfocusedBorderColor = Color(0xFFD0FF00)
                            ),
                            shape = RoundedCornerShape(15.dp),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            )
                        )

//                        DropdownMenu(
//                            expanded = offerRideUiState.expandedCapacity,
//                            onDismissRequest = { offerRideUiState.expandedCapacity = false },
//                            modifier = Modifier
//                                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
//                                .background(Color(0x33D0FF00))
//                        ) {
//                            offerRideViewModel.list.forEach {
//                                DropdownMenuItem(text = { Text(text = it) }, onClick = {
//                                    offerRideUiState.selectedCapacity = it
//                                    offerRideUiState.expandedCapacity = false
//                                })
//                            }
//                        }
                    }
                }

                Text(
                    modifier = Modifier
                        .padding(start = 18.dp, top = 24.dp)
                        .fillMaxWidth(),
                    text = "Car License Plate Number",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 0.dp, vertical = 8.dp),
                        value = carLicensePlate,
                        onValueChange = {
                            carLicensePlate = it
                        },
                        label = { Text(text = "Car License Plate Number") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFFD0FF00),
                            unfocusedBorderColor = Color(0xFFD0FF00)
                        ),
                        shape = RoundedCornerShape(15.dp)
                    )
                }

                Text(
                    modifier = Modifier
                        .padding(start = 18.dp, top = 24.dp)
                        .fillMaxWidth(),
                    text = "Add notes for this ride",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 0.dp, vertical = 8.dp),
                        value = notes,
                        onValueChange = {
                            notes = it
                        },
                        label = { Text(text = "Notes") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFFD0FF00),
                            unfocusedBorderColor = Color(0xFFD0FF00)
                        ),
                        shape = RoundedCornerShape(15.dp)
                    )
                }
            }
        }
        Surface {
            Column(
                modifier = Modifier
//                        .padding(top = 40.dp)
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                offerRideViewModel.TopShadow(alpha = .15f, height = 18.dp)

                Button(
                    modifier = Modifier
                        .padding(24.dp, 26.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(Color(0xFFD0FF00)),
                    onClick = {
                        // Insert function
                        offerRideViewModel.carType = carType
                        offerRideViewModel.carCapacity = carCapacity
                        offerRideViewModel.carLicensePlate = carLicensePlate
                        offerRideViewModel.notes = notes
                        offerRideViewModel.createRide(user.user_id, navController)
                    }
                ) {
                    Text(
                        text = "Offer Ride",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OfferRidePreview() {
//    OfferRideView()
}