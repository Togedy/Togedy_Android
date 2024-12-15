package com.example.togedy_android.data.remote.model.planner.resposne

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudyPlanResponseDto(
    @SerialName("studyPlanId")
    val studyPlanId: Int,
    @SerialName("name")
    val name: String,
    @SerialName("studyTagColor")
    val studyTagColor: String,
    @SerialName("studyTagId")
    val studyTagId: Int,
    @SerialName("planStatus")
    val planStatus: String,
    @SerialName("studyRecords")
    val studyRecords: List<List<String>>,
)