package com.example.togedy_android.presentation.planner.plannerDetail.state

import com.example.togedy_android.core.state.UiState
import com.example.togedy_android.domain.model.planner.DayOfPlan
import java.time.LocalDate

data class PlannerDetailUiState(
    val loadState: UiState<DayOfPlan> = UiState.Loading,
    val selectedDay: LocalDate = LocalDate.now(),
)
