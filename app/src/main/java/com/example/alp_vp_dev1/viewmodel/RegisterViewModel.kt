package com.example.alp_vp_dev1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.alp_vp_dev1.data.DataStoreManager
import com.example.alp_vp_dev1.model.User
import com.example.alp_vp_dev1.repository.AuthContainer
import com.example.alp_vp_dev1.view.ListScreen
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {

    fun Register(
        user: User,
        navController: NavController,
        dataStore: DataStoreManager
    ) {
        viewModelScope.launch {
            println("register button clicked")
            val registerUser = AuthContainer().authRepositories.create_user(user)
            println("register user: $registerUser")
            if (registerUser.contains("200")) {
                println("register view model status 200 mantap")
                navController.navigate(ListScreen.Login.name)
            } else {
                println("register view model status bukan 200")
            }
        }
    }

}