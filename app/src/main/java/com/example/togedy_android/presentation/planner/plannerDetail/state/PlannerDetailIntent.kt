package com.example.togedy_android.presentation.planner.plannerDetail.state

import java.time.LocalDate

sealed class PlannerDetailIntent {
    data class LoadDayPlan(val date: LocalDate) : PlannerDetailIntent()
}