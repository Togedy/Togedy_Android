package com.example.togedy_android.data.remote.model.planner.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudyGoalRequestDto(
    @SerialName("date")
    val date: String,
    @SerialName("targetTime")
    val targetTime: String,
)
