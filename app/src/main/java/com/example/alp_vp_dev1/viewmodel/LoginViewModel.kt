package com.example.alp_vp_dev1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.alp_vp_dev1.repository.AuthContainer
import com.example.alp_vp_dev1.view.ListScreen
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    fun Login(
        email: String,
        password: String,
        navController: NavController
    ) {
        viewModelScope.launch {
            val loggedIn = AuthContainer().authRepositories.login(email, password)
            navController.navigate(ListScreen.InputDestination.name)
        }
    }

}