package com.example.togedy_android.presentation.calendar.calendar.state

import java.time.LocalDate

data class CalendarDialogState(
    val isDailyScheduleDialogVisible: Boolean = false,
    val selectedDay: LocalDate = LocalDate.now()
)