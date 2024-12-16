package com.example.togedy_android.data.remote.model.calendar.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MonthlyScheduleItemDto(
    @SerialName("date")
    val date: String,
    @SerialName("scheduleName")
    val scheduleName: String,
    @SerialName("categoryColor")
    val categoryColor: String,
)
