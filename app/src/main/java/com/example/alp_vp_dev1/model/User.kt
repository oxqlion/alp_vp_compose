package com.example.alp_vp_dev1.model

data class User(
    val message: String = "",
    val status: Int = -1,
    val user_id: Int = -1,
    val email: String,
    val password: String,
    val name: String = "",
    val phone: String = "",
    val driver: String = "",
)