package com.example.alp_vp_dev1.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alp_vp_dev1.viewmodel.HistoryViewModel

@Composable
fun HistoryView(
    historyViewModel: HistoryViewModel = viewModel()
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

        LazyVerticalGrid(columns = GridCells.Fixed(1)){
            item{
                historyViewModel.HistoryCard()
            }
        }
    }

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
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HistoryPreview(){
    HistoryView()
}