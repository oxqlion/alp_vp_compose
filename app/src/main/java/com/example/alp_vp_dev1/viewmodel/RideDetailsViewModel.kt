package com.example.alp_vp_dev1.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alp_vp_dev1.model.RideModel
import com.example.alp_vp_dev1.repository.RideContainer
import kotlinx.coroutines.launch

sealed interface RideDetailsUIState {
    data class Success(val data: RideModel) : RideDetailsUIState
    object Error : RideDetailsUIState
    object Loading : RideDetailsUIState
}

class RideDetailsViewModel() : ViewModel() {

    var rideDetailsUIState: RideDetailsUIState by mutableStateOf(RideDetailsUIState.Loading)
        private set

    private lateinit var data: RideModel

    fun loadRideDetails(rideId: Int) {
        viewModelScope.launch {
            try {
                println("trying to fetch data in ride details repo")
                data = RideContainer().rideRepositories.getRideDetails(rideId)
                println("returned data : $data")
                rideDetailsUIState = RideDetailsUIState.Success(data)
            } catch (e: Exception) {
                println("error getting ride details in ride details viewmodel")
                rideDetailsUIState = RideDetailsUIState.Error
            }
        }
    }

}