package com.example.alp_vp_dev1.viewmodel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.alp_vp_dev1.model.OfferRide
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class OfferRideViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(OfferRide())
    val uiState: StateFlow<OfferRide> = _uiState.asStateFlow()

    val list = listOf("date", "date2")

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