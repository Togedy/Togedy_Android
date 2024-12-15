package com.example.togedy_android.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudyGoalDateRequestDto(
    @SerialName("date")
    val date: String,
)
