package com.example.togedy_android.data.remote.model.calendar.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryIdResponseDto(
    @SerialName("categoryId")
    val categoryId: Int,
)
