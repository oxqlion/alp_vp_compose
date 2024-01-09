package com.example.alp_vp_dev1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.alp_vp_dev1.data.DataStoreManager
import com.example.alp_vp_dev1.view.ListScreen
import kotlinx.coroutines.launch

class ProfileViewModel(): ViewModel() {

    fun logout(
        dataStore: DataStoreManager,
        navController: NavController
    ) {
        println("button logout dipencet di viewmodel")
        viewModelScope.launch {
            println("coba launch clear datastore di profile viewmodel")
            dataStore.clearDataStore()
            navController.navigate(ListScreen.Login.name)
        }
    }

}