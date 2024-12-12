package com.example.togedy_android.domain.model.planner

data class PlanItem(
    val todoID: Int,
    val subjectColor: String,
    val subjectId: Int = -3,
    val title: String,
    val status: String,
)
