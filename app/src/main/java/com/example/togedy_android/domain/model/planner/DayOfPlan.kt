package com.example.togedy_android.domain.model.planner

data class DayOfPlan(
    val planList: List<PlanItem>,
    val timeline: List<List<String>>,
)
