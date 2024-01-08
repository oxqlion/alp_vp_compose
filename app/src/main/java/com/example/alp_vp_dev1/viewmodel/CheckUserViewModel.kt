package com.example.alp_vp_dev1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.alp_vp_dev1.data.DataStoreManager
import com.example.alp_vp_dev1.model.User
import com.example.alp_vp_dev1.repository.AuthContainer
import com.example.alp_vp_dev1.view.ListScreen
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class CheckUserViewModel() : ViewModel() {
    init {
        println("checking user ...")
    }

    fun checkUser(dataStore: DataStoreManager, navController: NavController): Int {
        var dataStoreInUser: User? = null
        var userExist: Int = 0;

        viewModelScope.launch {
            println("mencoba menjalankan launc di check user viewmodel")

            dataStoreInUser = dataStore.getUser.first()!!

            var userObj: User? = null

            if (dataStoreInUser == null) {
                println("di datastore gaada user, redirecting to login")
                navController.navigate(ListScreen.Login.name)
            } else {
                println("di datastore ada user : $dataStoreInUser, verifying ke database...")
                val tryGetUserFromLogin =
                    AuthContainer().authRepositories.check_user(dataStoreInUser!!.user_id)
                println("try get user from login check use viwe model : $tryGetUserFromLogin")

                if (tryGetUserFromLogin.contains("200")) {
                    println("ada user yang sama di database, returning 1:D")

                    userExist = 1;

                    navController.navigate(ListScreen.Home.name)

//                    userObj = User(
//                        user_id = tryGetUserFromLogin.substringAfter("user_id=")
//                            .substringBefore(",").toInt(),
//                        email = tryGetUserFromLogin.substringAfter("email=")
//                            .substringBefore(","),
//                        password = "",
//                        name = tryGetUserFromLogin.substringAfter("name=")
//                            .substringBefore(","),
//                        phone = tryGetUserFromLogin.substringAfter("phone=")
//                            .substringBefore(","),
//                        driver = tryGetUserFromLogin.substringAfter("driver=")
//                            .substringBefore(")")
//                    )
                } else {
                    println("gaada user di database, returning 0 ...")
                    userExist = 0
                    navController.navigate(ListScreen.Login.name)
                }

//                if (userObj != null) {
//                    if (dataStoreInUser!!.user_id == userObj.user_id) {
//                        userExist = 1;
//                    }
//                } else {
////                    navController.navigate(ListScreen.Login.name)
//                    userExist = 0;
//                }
            }
        }
        return userExist;
    }
}