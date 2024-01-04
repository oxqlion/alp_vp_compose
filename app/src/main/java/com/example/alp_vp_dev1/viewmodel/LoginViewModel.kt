package com.example.alp_vp_dev1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.alp_vp_dev1.model.User
import com.example.alp_vp_dev1.repository.AuthContainer
import com.example.alp_vp_dev1.view.ListScreen
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    fun Login(
        user: User,
        navController: NavController
    ) {
        viewModelScope.launch {
            println("halo tombol login kepencet di awal")
            val loggedIn = AuthContainer().authRepositories.login(user)
            println("halo tombol login kepencet")
            navController.navigate(ListScreen.InputDestination.name)
        }
    }
}