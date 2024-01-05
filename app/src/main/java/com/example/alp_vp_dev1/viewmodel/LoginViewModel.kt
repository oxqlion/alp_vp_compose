package com.example.alp_vp_dev1.viewmodel

import android.annotation.SuppressLint
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.alp_vp_dev1.data.DataStoreManager
import com.example.alp_vp_dev1.model.User
import com.example.alp_vp_dev1.repository.AuthContainer
import com.example.alp_vp_dev1.view.ListScreen
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {

    fun Login(
        user: User,
        navController: NavController,
        dataStore: DataStoreManager
    ) {
        viewModelScope.launch {
            println("login button clicked, trying to login")
            val loggedInUser = AuthContainer().authRepositories.login(user)
            println("logged in user : $loggedInUser")
            if (loggedInUser.contains("200")) {
                println("there is a user")
                val userJson = loggedInUser.substringAfter(",")
                println("substringed userJson : $userJson")
//                val gson = Gson()
                val userObj = User(
                    user_id = loggedInUser.substringAfter("user_id=").substringBefore(",").toInt(),
                    email = loggedInUser.substringAfter("email=").substringBefore(","),
                    password = "",
                    name = loggedInUser.substringAfter("name=").substringBefore(","),
                    phone = loggedInUser.substringAfter("phone=").substringBefore(","),
                    driver = loggedInUser.substringAfter("driver=").substringBefore(")")
                )
                dataStore.saveUser(userObj)
                println("trying to save user to data store")
                val savedUser = dataStore.getUser.first()
                if (savedUser != null) {
                    println("user saved to data store")
                    println("saved user object : ${dataStore.getUser.first()}")
                    println("redirecting ...")
                    navController.navigate(ListScreen.InputDestination.name)
                } else println("no user in data store")
            } else {
                println("wrong password or no user found")
            }
        }
    }
}