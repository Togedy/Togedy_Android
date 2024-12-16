package com.example.togedy_android.presentation.calendar.calendarDialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togedy_android.domain.model.calendar.CategoryItem
import com.example.togedy_android.domain.model.calendar.DailyScheduleItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarDialogViewModel @Inject constructor(

): ViewModel() {
    private val _scheduleItems: MutableStateFlow<List<DailyScheduleItem>> =
        MutableStateFlow(emptyList())
    val scheduleItems: StateFlow<List<DailyScheduleItem>> = _scheduleItems.asStateFlow()

    fun getDailyScheduleItemList(selectedDay: LocalDate) {
        viewModelScope.launch{
            _scheduleItems.value = listOf(
                DailyScheduleItem(
                    id = 1,
                    name = "입시 설명회",
                    memo = "12:00",
                    startDate = "2024-12-16",
                    category = CategoryItem(
                        id = 1,
                        name = "건국대학교",
                        color = "color4"
                    )
                ),
                DailyScheduleItem(
                    id = 1,
                    name = "입시 설명회",
                    memo = "12:00",
                    startDate = "2024-12-16",
                    category = CategoryItem(
                        id = 1,
                        name = "건국대학교",
                        color = "color4"
                    )
                ),
                DailyScheduleItem(
                    id = 1,
                    name = "입시 설명회",
                    memo = "12:00",
                    startDate = "2024-12-16",
                    category = CategoryItem(
                        id = 1,
                        name = "건국대학교",
                        color = "color4"
                    )
                )
            )
        }
    }
}