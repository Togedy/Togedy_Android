package com.example.togedy_android.presentation.planner.state

import com.example.togedy_android.core.state.UiState
//import com.example.togedy_android.domain.entity.PlannerHomeInformation
import java.time.LocalDate

data class PlannerUiState(
//    val loadState: UiState<PlannerHomeInformation>,
    val selectedDate: LocalDate,
)
