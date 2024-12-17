package com.example.togedy_android.data.remote.model.planner.resposne

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudyTagItemResponseDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("color")
    val color: String,
)