package com.example.alp_vp_dev1.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RideDetailsView() {

    val singapore = LatLng(1.35, 103.87)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 15f)
    }
    val markerState = rememberMarkerState(
        position = singapore
    )

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(true) }

    val scaffoldState = rememberBottomSheetScaffoldState()

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
                state = markerState
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
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = {/* TODO */ },
                modifier = Modifier
                    .padding(24.dp)
                    .size(32.dp)
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        }

        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetContainerColor = Color.White,
            sheetShadowElevation = 24.dp,
            sheetPeekHeight = 150.dp,
            sheetContent = {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(24.dp)
                ) {
                    Text(
                        text = "Ride Details",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(6.dp, 0.dp)
                            .fillMaxWidth()
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
                onClick = { /* Handle button click */ },
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RideDetailsPreview() {
    RideDetailsView()
}