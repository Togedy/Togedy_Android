package com.example.togedy_android.presentation.planner.planner.state

import com.example.togedy_android.core.state.UiState
import com.example.togedy_android.domain.model.planner.PlannerHomeInformation
import com.example.togedy_android.domain.model.planner.StudyGoal
import java.time.LocalDate

data class PlannerUiState(
    val loadState: UiState<PlannerHomeInformation> = UiState.Loading,
    val studyGoalState: UiState<StudyGoal> = UiState.Empty,
    val selectedDay: LocalDate = LocalDate.now(),
)
