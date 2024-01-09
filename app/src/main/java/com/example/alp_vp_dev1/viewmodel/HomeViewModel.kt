package com.example.alp_vp_dev1.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alp_vp_dev1.model.RideModel
import com.example.alp_vp_dev1.repository.RideContainer
import kotlinx.coroutines.launch

sealed interface HomeUIState {
    data class Success(val data: List<RideModel>) : HomeUIState
    object Error : HomeUIState
    object Loading : HomeUIState
}

class HomeViewModel() : ViewModel() {

    var homeUIState: HomeUIState by mutableStateOf(HomeUIState.Loading)
        private set

    private lateinit var data: List<RideModel>

    init {
        loadRides()
    }

    fun loadRides() {
        viewModelScope.launch {
            try {
                println("masuk ke load ride viwemodel try")
                data = RideContainer().rideRepositories.rides()
                homeUIState = HomeUIState.Success(data)
            } catch (e: Exception) {
                println("in status success homeviewmodel")
                println("load rides home viewmodel error : ${e.message}")
                homeUIState = HomeUIState.Error
            }
        }
    }
}