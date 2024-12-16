package com.example.togedy_android.data.remote.model.calendar.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyScheduleItemDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("memo")
    val memo: String,
    @SerialName("startDate")
    val startDate: String,
    @SerialName("endDate")
    val endDate: String? = null,
    @SerialName("category")
    val category: CategoryItemResponseDto,
)