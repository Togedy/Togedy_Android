package com.example.togedy_android.data.remote.model.planner.resposne

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudyPlanIdResponseDto(
    @SerialName("studyPlanId")
    val studyPlanId: Int,
)
