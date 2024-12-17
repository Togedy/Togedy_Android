package com.example.togedy_android.data.remote.model.calendar.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryItemRequestDto(
    @SerialName("name")
    val name: String,
    @SerialName("color")
    val color: String,
)
