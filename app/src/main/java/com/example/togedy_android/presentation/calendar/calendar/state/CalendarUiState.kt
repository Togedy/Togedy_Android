package com.example.togedy_android.presentation.calendar.calendar.state

import com.example.togedy_android.core.state.UiState
import com.example.togedy_android.domain.model.calendar.MonthlyScheduleItem
import java.time.LocalDate

data class CalendarUiState(
    val loadState: UiState<CalendarHomeInformation> = UiState.Loading,
    val selectedDay: LocalDate = LocalDate.now(),
    val isFabExpanded: Boolean = false,
)

data class CalendarHomeInformation(
    val monthlyScheduleItems: List<MonthlyScheduleItem>
)