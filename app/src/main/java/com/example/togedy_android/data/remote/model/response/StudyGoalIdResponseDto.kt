package com.example.togedy_android.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudyGoalIdResponseDto(
    @SerialName("studyGoalId")
    val studyGoalId: Int,
)
