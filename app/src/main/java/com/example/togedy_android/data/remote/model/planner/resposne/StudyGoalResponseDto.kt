package com.example.togedy_android.data.remote.model.planner.resposne

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudyGoalResponseDto(
    @SerialName("id")
    val id: Int,
    @SerialName("targetTime")
    val targetTime: String,
    @SerialName("actualTime")
    val actualTime: String,
    @SerialName("achievement")
    val achievement: Int,
)
