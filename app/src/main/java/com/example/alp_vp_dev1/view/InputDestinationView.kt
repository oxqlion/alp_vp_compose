package com.example.alp_vp_dev1.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
    navigate: () -> Unit
) {

    val inputDestinationUiState by inputDestinationViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .background(Color(0xFFD0FF00))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, top = 25.dp, end = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardBackspace,
                contentDescription = "Back"
            )

            Text(
                text = "Ride Destination",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
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
                .fillMaxWidth()
                .background(Color(0XFFFFFFFF), RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp))
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 24.dp, top = 28.dp)
                    .fillMaxWidth(),
                text = "Input Pick Up Point",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            inputDestinationViewModel.TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                value = inputDestinationUiState.pickUpPoint,
                onValueChanged = { inputDestinationUiState.pickUpPoint = it },
                text = "Your pick up point ...",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

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
                value = inputDestinationUiState.destinationPoint,
                onValueChanged = { inputDestinationUiState.destinationPoint = it },
                text = "Your destination point ...",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

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
                value = inputDestinationUiState.totalPassenger,
                onValueChanged = { inputDestinationUiState.totalPassenger = it },
                text = "0",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
        }

        Surface {
            Column(
                modifier = Modifier
                    .padding(top = 34.dp)
                    .fillMaxSize()
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
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Rp 12.000",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    modifier = Modifier
                        .padding(24.dp, 8.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(Color(0xFFD0FF00)),
                    onClick = { navigate() }
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InputDestinationPreview() {
//    InputDestinationView(navigate = {})
}