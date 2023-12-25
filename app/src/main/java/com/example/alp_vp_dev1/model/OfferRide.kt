package com.example.alp_vp_dev1.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.ui.graphics.vector.ImageVector

data class OfferRide (
    var standbyPoint: String = "",
    var destination: String = "",

    var expandedDate: Boolean = false,
    var selectedDate: String = "",

    var expandedTime: Boolean = false,
    var selectedTime: String = "",

    var expandedType: Boolean = false,
    var selectedType: String = "",

    var expandedCapacity: Boolean = false,
    var selectedCapacity: String = "",

    val iconDate: ImageVector = if (expandedDate) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    },

    val iconTime: ImageVector = if (expandedTime) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    },

    val iconType: ImageVector = if (expandedType) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    },

    val iconCapacity: ImageVector = if (expandedCapacity) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

)