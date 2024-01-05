package com.example.alp_vp_dev1.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alp_vp_dev1.R

//val inter = FontFamily(
//    Font(R.font.inter_variable, FontWeight.Normal),
//    Font(R.font.inter_variable_italic, FontWeight.Normal)
//)

@Composable
fun HomeView() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(onClick = {  }) {
            Text(text = "clear data store")
        }
    }
}

@Composable
fun HomeCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .shadow(10.dp, shape = RoundedCornerShape(5.dp)),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "Today, 17:00 WIB",
//                    fontFamily = inter,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .absolutePadding(top = 5.dp, left = 10.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.mobil),
                    contentDescription = "Mobil",
                    modifier = Modifier
                        .size(170.dp)
                        .padding(10.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
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
                        .size(20.dp, 130.dp)
                        .graphicsLayer(scaleY = 1f),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .absolutePadding(bottom = 5.dp, left = 5.dp)
                    .size(100.dp, 150.dp)
            )
            {
                Text(
                    text = "Citraland CBD Boulevard, Made",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .absolutePadding(top = 10.dp)
                )
                Text(
                    text = "Standby point",
                    fontSize = 10.sp,
                    color = Color.Gray,
                    modifier = Modifier
                )
                Text(
                    text = "Jl. Mayjend. Jonosewojo No.2",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .absolutePadding(top = 45.dp)
                )
                Text(
                    text = "Destination",
                    fontSize = 10.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .absolutePadding(bottom = 5.dp)
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .absolutePadding(bottom = 5.dp)
                    .size(150.dp)
            ) {

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
