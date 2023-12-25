package com.example.alp_vp_dev1.view

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardBackspace
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alp_vp_dev1.viewmodel.OfferRideViewModel
import androidx.compose.ui.unit.toSize
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
    offerRideViewModel: OfferRideViewModel = viewModel()
) {
    val offerRideUiState by offerRideViewModel.uiState.collectAsState()

    val textFieldSize by remember { mutableStateOf(Size.Zero) }

//    val calendarState = rememberSheetState()
//    CalendarDialog(
//        state = calendarState,
//        config = CalendarConfig(
//            monthSelection = true,
//            yearSelection = true
//        ),
//        selection = CalendarSelection.Date { date ->
//            selectedDate = date.toString()
//            Log.d("Selected date : ", "$date")
//        }
//    )
//
//    val clockState = rememberSheetState()
//    ClockDialog(
//        state = clockState,
//        config = ClockConfig(
//            is24HourFormat = true
//        ),
//        selection = ClockSelection.HoursMinutes { hours, minutes ->
//            selectedTime = "$hours:$minutes"
//        }
//    )

    Column(
        modifier = Modifier
            .background(Color(0xFFD0FF00))
    ) {
        Icon(
            modifier = Modifier
                .padding(start = 25.dp, top = 25.dp, end = 25.dp),
            imageVector = Icons.Filled.KeyboardBackspace,
            contentDescription = "Back"
        )

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Offer Ride",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            textAlign = TextAlign.Center
        )

        Column(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth()
                .background(Color(0XFFFFFFFF), RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp))
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
                value = offerRideUiState.standbyPoint,
                onValueChanged = { offerRideUiState.standbyPoint = it },
                text = "Your address point ...",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

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
                value = offerRideUiState.destination,
                onValueChanged = { offerRideUiState.destination = it },
                text = "Your destination ...",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

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

//                DateTime() {
//                    Column(
//                        modifier = Modifier
//                            .padding(start = 24.dp, top = 8.dp)
//                    ) {
//                        OutlinedTextField(
//                            modifier = Modifier
//                                .width(160.dp)
//                                .clickable { offerRideViewModel.calendarState.show() },
//                            value = selectedDate,
//                            onValueChange = { selectedDate = it },
//                            label = { Text(text = "Date") },
//                            trailingIcon = {
//                                Icon(
//                                    iconDate,
//                                    contentDescription = "arrow",
//                                    modifier = Modifier
//                                        .clickable { expandedDate = !expandedDate }
//                                )
//                            },
//                            enabled = false,
//                            colors = TextFieldDefaults.outlinedTextFieldColors(
//                                focusedBorderColor = Color(0xFFD0FF00),
//                                unfocusedBorderColor = Color(0xFFD0FF00),
//                                disabledBorderColor = Color(0xFFD0FF00),
//                                disabledLabelColor = Color(0xFF333138),
//                                textColor = Color(0xFF000103),
//                                placeholderColor = Color(0xFF000103)
//                            ),
//                            shape = RoundedCornerShape(15.dp)
//                        )
//
//                        DropdownMenu(
//                            expanded = expandedDate,
//                            onDismissRequest = { expandedDate = false },
//                            modifier = Modifier
//                                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
//                                .background(Color(0x33D0FF00))
//                                .clickable {
//                                    expandedDate = true
//                                }
//                        ) {
//                            list.forEach {
//                                DropdownMenuItem(text = { Text(text = it) }, onClick = {
//                                    selectedDate = it
//                                    expandedDate = false
//                                }
//                                )
//                            }
//                        }
//                    }
//
//                    Column(
//                        modifier = Modifier
//                            .padding(end = 24.dp, top = 8.dp, bottom = 8.dp)
//                    ) {
//                        OutlinedTextField(
//                            modifier = Modifier
//                                .width(160.dp)
//                                .clickable { clockState.show() },
//                            value = selectedTime,
//                            onValueChange = { selectedTime = it },
//                            label = { Text(text = "Time") },
//                            trailingIcon = {
//                                Icon(
//                                    iconTime,
//                                    contentDescription = "arrow",
//                                    modifier = Modifier
//                                        .clickable { expandedTime = !expandedTime }
//                                )
//                            },
//                            enabled = false,
//                            colors = TextFieldDefaults.outlinedTextFieldColors(
//                                focusedBorderColor = Color(0xFFD0FF00),
//                                unfocusedBorderColor = Color(0xFFD0FF00),
//                                disabledBorderColor = Color(0xFFD0FF00),
//                                disabledLabelColor = Color(0xFF333138),
//                                textColor = Color(0xFF000103),
//                                placeholderColor = Color(0xFF000103)
//                            ),
//                            shape = RoundedCornerShape(15.dp)
//                        )
//
//                        DropdownMenu(
//                            expanded = expandedTime,
//                            onDismissRequest = { expandedTime = false },
//                            modifier = Modifier
//                                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
//                                .background(Color(0x33D0FF00))
//                        ) {
//                            list.forEach {
//                                DropdownMenuItem(text = { Text(text = it) }, onClick = {
//                                    selectedTime = it
//                                    expandedTime = false
//                                }
//                                )
//                            }
//                        }
//                    }
//                }
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
                        value = offerRideUiState.selectedType,
                        onValueChange = { offerRideUiState.selectedType = it },
                        label = { Text(text = "Car Type") },
                        trailingIcon = {
                            Icon(
                                offerRideUiState.iconType,
                                contentDescription = "arrow",
                                modifier = Modifier
                                    .clickable {
                                        offerRideUiState.expandedType =
                                            !offerRideUiState.expandedType
                                    }
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFFD0FF00),
                            unfocusedBorderColor = Color(0xFFD0FF00)
                        ),
                        shape = RoundedCornerShape(15.dp)
                    )

                    DropdownMenu(
                        expanded = offerRideUiState.expandedType,
                        onDismissRequest = { offerRideUiState.expandedType = false },
                        modifier = Modifier
                            .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                            .background(Color(0x33D0FF00))
                    ) {
                        offerRideViewModel.list.forEach {
                            DropdownMenuItem(text = { Text(text = it) }, onClick = {
                                offerRideUiState.selectedType = it
                                offerRideUiState.expandedType = false
                            }
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(end = 24.dp, top = 8.dp)
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .width(100.dp),
                        value = offerRideUiState.selectedCapacity,
                        onValueChange = { offerRideUiState.selectedCapacity = it },
                        label = { Text(text = "1") },
                        trailingIcon = {
                            Icon(
                                offerRideUiState.iconCapacity,
                                contentDescription = "arrow",
                                modifier = Modifier
                                    .clickable {
                                        offerRideUiState.expandedCapacity =
                                            !offerRideUiState.expandedCapacity
                                    }
                            )
                        },
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

                    DropdownMenu(
                        expanded = offerRideUiState.expandedCapacity,
                        onDismissRequest = { offerRideUiState.expandedCapacity = false },
                        modifier = Modifier
                            .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                            .background(Color(0x33D0FF00))
                    ) {
                        offerRideViewModel.list.forEach {
                            DropdownMenuItem(text = { Text(text = it) }, onClick = {
                                offerRideUiState.selectedCapacity = it
                                offerRideUiState.expandedCapacity = false
                            })
                        }
                    }
                }
            }

            Surface {
                Column(
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .fillMaxSize()
                ) {
                    offerRideViewModel.TopShadow(alpha = .15f, height = 18.dp)

                    Button(
                        modifier = Modifier
                            .padding(24.dp, 26.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(Color(0xFFD0FF00)),
                        onClick = { /*TODO*/ }
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
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OfferRidePreview() {
    OfferRideView()
}