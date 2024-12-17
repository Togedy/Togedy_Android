package com.example.togedy_android.presentation.calendar.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togedy_android.core.state.UiState
import com.example.togedy_android.presentation.calendar.calendar.state.CalendarDialogState
import com.example.togedy_android.presentation.calendar.calendar.state.CalendarHomeInformation
import com.example.togedy_android.presentation.calendar.calendar.state.CalendarUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(

): ViewModel() {
    private val _uiState = MutableStateFlow(CalendarUiState())
    val uiState = _uiState.asStateFlow()

    private val _dialogState: MutableStateFlow<CalendarDialogState> =
        MutableStateFlow(CalendarDialogState())
    val dialogState: StateFlow<CalendarDialogState> = _dialogState.asStateFlow()

    fun getCalendarHomeInformation(selectedDay: LocalDate) {
        viewModelScope.launch {
            //api 연결
        }
        updateLoadState(
            loadState = UiState.Success(
                CalendarHomeInformation(
                    emptyList()
                )
            )
        )
    }

    fun updateIsFabExpanded(result: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                isFabExpanded = result
            )
        }
    }

    fun updateSelectedDay(selectedDay: LocalDate) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedDay = selectedDay
            )
        }

        _dialogState.update { currentState ->
            currentState.copy(
                selectedDay = selectedDay
            )
        }
    }

    private fun updateLoadState(loadState: UiState<CalendarHomeInformation>) =
        _uiState.update { currentState ->
            currentState.copy(
                loadState = loadState
            )
        }

    fun updateDialogVisibility() {
        _dialogState.update {
            it.copy(isDailyScheduleDialogVisible = !_dialogState.value.isDailyScheduleDialogVisible)
        }
    }
}