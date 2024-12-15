package com.example.togedy_android.data.remote.model.planner.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudyPlanRequestDto(
    @SerialName("name")
    val name: String,
    @SerialName("date")
    val date: String,
    @SerialName("studyTagId")
    val studyTagId: Int,
)