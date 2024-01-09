package com.example.alp_vp_dev1.viewmodel

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alp_vp_dev1.R
import com.example.alp_vp_dev1.model.RideModel
import com.example.alp_vp_dev1.repository.RideContainer
import kotlinx.coroutines.launch

sealed interface HistoryUIState {
    data class Success(val data: List<RideModel>) : HistoryUIState
    object Error : HistoryUIState
    object Loading : HistoryUIState
}

class HistoryViewModel: ViewModel() {

    var historyUIState: HistoryUIState by mutableStateOf(HistoryUIState.Loading)
        private set

    private lateinit var data: List<RideModel>

    fun userRides(userId: Int) {
        viewModelScope.launch {
            try {
                println("masuk ke load ride viwemodel try")
                data = RideContainer().rideRepositories.userRides(userId)
                historyUIState = HistoryUIState.Success(data)
            } catch (e: Exception) {
                println("in status success homeviewmodel")
                println("load rides home viewmodel error : ${e.message}")
                historyUIState = HistoryUIState.Error
            }
        }
    }

/*
    @Composable
    fun HistoryCard() {
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
                        text = "Today, 17:00 WIB",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )

                    Column {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "L 1782 AB",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            textAlign = TextAlign.End
                        )

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Suzuki Ertiga",
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
                            text = "CitraLand CBD Boulevard, Made",
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
                            text = "Jl. Mayjend Jonosewojo No.2",
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Destination",
                            fontWeight = FontWeight.Light,
                            fontSize = 11.sp
                        )
                    }

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
    }*/
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
}