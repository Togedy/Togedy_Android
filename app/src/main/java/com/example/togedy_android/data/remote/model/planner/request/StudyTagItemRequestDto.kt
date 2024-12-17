package com.example.togedy_android.data.remote.model.planner.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudyTagItemRequestDto(
    @SerialName("name")
    val name: String,
    @SerialName("color")
    val color: String,
)
