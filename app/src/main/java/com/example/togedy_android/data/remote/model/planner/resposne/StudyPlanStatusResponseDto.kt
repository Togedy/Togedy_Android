package com.example.togedy_android.data.remote.model.planner.resposne

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudyPlanStatusResponseDto(
    @SerialName("id")
    val id: Int,
    @SerialName("status")
    val status: String,
)
