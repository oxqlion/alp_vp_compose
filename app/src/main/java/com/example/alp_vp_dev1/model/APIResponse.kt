package com.example.alp_vp_dev1.model

data class APIResponse(
    val status: Int = -1,
    val message: String = "",
    val ride_id: String = "",
    val data: Any = ""
)
