package com.example.alp_vp_dev1.view

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.KeyboardBackspace
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.readable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import java.time.Clock

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OfferRideView() {


    var standbyPoint by rememberSaveable { mutableStateOf("") }
    var destination by rememberSaveable { mutableStateOf("") }

    val list = listOf("date", "date2")
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    var expandedDate by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf("") }

    var expandedTime by remember { mutableStateOf(false) }
    var selectedTime by remember { mutableStateOf("") }

    var expandedType by remember { mutableStateOf(false) }
    var selectedType by remember { mutableStateOf("") }

    var expandedCapacity by remember { mutableStateOf(false) }
    var selectedCapacity by remember { mutableStateOf("") }

    val iconDate = if (expandedDate) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    val iconTime = if (expandedTime) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    val iconType = if (expandedTime) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    val iconCapacity = if (expandedTime) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    val calendarState = rememberSheetState()
    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true
        ),
        selection = CalendarSelection.Date { date ->
            selectedDate = date.toString()
            Log.d("Selected date : ", "$date")
        }
    )

    val clockState = rememberSheetState()
    ClockDialog(
        state = clockState,
        config = ClockConfig(
            is24HourFormat = true
        ),
        selection = ClockSelection.HoursMinutes { hours, minutes ->
            selectedTime = "$hours:$minutes"
        }
    )

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

            AddressField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                value = standbyPoint,
                onValueChanged = { standbyPoint = it },
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

            AddressField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                value = destination,
                onValueChanged = { destination = it },
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
                Column(
                    modifier = Modifier
                        .padding(start = 24.dp, top = 8.dp)
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .width(160.dp)
                            .clickable { calendarState.show() },
                        value = selectedDate,
                        onValueChange = { selectedDate = it },
                        label = { Text(text = "Date") },
                        trailingIcon = {
                            Icon(
                                iconDate,
                                contentDescription = "arrow",
                                modifier = Modifier
                                    .clickable { expandedDate = !expandedDate }
                            )
                        },
                        enabled = false,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFFD0FF00),
                            unfocusedBorderColor = Color(0xFFD0FF00),
                            disabledBorderColor = Color(0xFFD0FF00),
                            disabledLabelColor = Color(0xFF333138),
                            textColor = Color(0xFF000103),
                            placeholderColor = Color(0xFF000103)
                        ),
                        shape = RoundedCornerShape(15.dp)
                    )

                    DropdownMenu(
                        expanded = expandedDate,
                        onDismissRequest = { expandedDate = false },
                        modifier = Modifier
                            .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                            .background(Color(0x33D0FF00))
                            .clickable {
                                expandedDate = true
                            }
                    ) {
                        list.forEach {
                            DropdownMenuItem(text = { Text(text = it) }, onClick = {
                                selectedDate = it
                                expandedDate = false
                            }
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(end = 24.dp, top = 8.dp, bottom = 8.dp)
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .width(160.dp)
                            .clickable { clockState.show() },
                        value = selectedTime,
                        onValueChange = { selectedTime = it },
                        label = { Text(text = "Time") },
                        trailingIcon = {
                            Icon(
                                iconTime,
                                contentDescription = "arrow",
                                modifier = Modifier
                                    .clickable { expandedTime = !expandedTime }
                            )
                        },
                        enabled = false,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFFD0FF00),
                            unfocusedBorderColor = Color(0xFFD0FF00),
                            disabledBorderColor = Color(0xFFD0FF00),
                            disabledLabelColor = Color(0xFF333138),
                            textColor = Color(0xFF000103),
                            placeholderColor = Color(0xFF000103)
                        ),
                        shape = RoundedCornerShape(15.dp)
                    )

                    DropdownMenu(
                        expanded = expandedTime,
                        onDismissRequest = { expandedTime = false },
                        modifier = Modifier
                            .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                            .background(Color(0x33D0FF00))
                    ) {
                        list.forEach {
                            DropdownMenuItem(text = { Text(text = it) }, onClick = {
                                selectedTime = it
                                expandedTime = false
                            }
                            )
                        }
                    }
                }
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
                            .width(200.dp),
                        value = selectedType,
                        onValueChange = { selectedType = it },
                        label = { Text(text = "Notes") },
//                        trailingIcon = {
//                            Icon(
//                                iconType,
//                                contentDescription = "arrow",
//                                modifier = Modifier
//                                    .clickable { expandedType = !expandedType }
//                            )
//                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFFD0FF00),
                            unfocusedBorderColor = Color(0xFFD0FF00)
                        ),
                        shape = RoundedCornerShape(15.dp)
                    )

                    DropdownMenu(
                        expanded = expandedType,
                        onDismissRequest = { expandedType = false },
                        modifier = Modifier
                            .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                            .background(Color(0x33D0FF00))
                    ) {
                        list.forEach {
                            DropdownMenuItem(text = { Text(text = it) }, onClick = {
                                selectedType = it
                                expandedType = false
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
                            .width(120.dp),
                        value = selectedCapacity,
                        onValueChange = { selectedCapacity = it },
                        label = { Text(text = "Person") },
//                        trailingIcon = {
//                            Icon(
//                                iconCapacity,
//                                contentDescription = "arrow",
//                                modifier = Modifier
//                                    .clickable { expandedCapacity = !expandedCapacity }
//                            )
//                        },
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
                        expanded = expandedCapacity,
                        onDismissRequest = { expandedCapacity = false },
                        modifier = Modifier
                            .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                            .background(Color(0x33D0FF00))
                    ) {
                        list.forEach {
                            DropdownMenuItem(text = { Text(text = it) }, onClick = {
                                selectedCapacity = it
                                expandedCapacity = false
                            }
                            )
                        }
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
                TopShadow(alpha = .15f, height = 18.dp)

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

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OfferRidePreview() {
    OfferRideView()
}