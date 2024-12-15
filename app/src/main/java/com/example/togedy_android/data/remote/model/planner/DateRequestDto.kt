package com.example.togedy_android.data.remote.model.planner

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DateRequestDto(
    @SerialName("date")
    val date: String,
)
