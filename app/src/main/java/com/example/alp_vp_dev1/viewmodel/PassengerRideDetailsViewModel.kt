package com.example.alp_vp_dev1.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alp_vp_dev1.model.PassengerRideDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface PassengerRideDetailsUIState{
    object Success: PassengerRideDetailsUIState
    object Error: PassengerRideDetailsUIState
    object Loading: PassengerRideDetailsUIState
}

class PassengerRideDetailsViewModel(): ViewModel() {

    var passengerRideDetailUIState: PassengerRideDetailsUIState by mutableStateOf(PassengerRideDetailsUIState.Success)
        private set

    private val _uiState = MutableStateFlow(PassengerRideDetail())
    val uiState: StateFlow<PassengerRideDetail> = _uiState.asStateFlow()

    fun rideDetailView() {
        viewModelScope.launch {
            passengerRideDetailUIState = PassengerRideDetailsUIState.Success
        }
    }
}