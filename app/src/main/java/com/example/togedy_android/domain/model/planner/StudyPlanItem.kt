package com.example.togedy_android.domain.model.planner

data class StudyPlanItem(
    val studyPlanId: Int,
    val name: String,
    val studyTagColor: String,
    val studyTagId: Int,
    val planStatus: String,
    val studyRecords: List<List<String>>
)
