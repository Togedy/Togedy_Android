package com.example.togedy_android.ui.component.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.ui.theme.TogedyTheme
import com.example.togedy_android.ui.theme.white
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

@Composable
fun MonthlyCalendar(
    onDaySelected: (LocalDate) -> Unit
) {
    Column(
        modifier = Modifier.background(white)
    ) {
        val today = LocalDate.now()
        var selectedDay by remember { mutableStateOf(today) }

        val firstDayOfMonth = selectedDay.withDayOfMonth(1)
        val lastDayOfMonth =
            LocalDate.of(selectedDay.year, selectedDay.month, selectedDay.lengthOfMonth())
        val weeksInMonth = mutableListOf<LocalDate>()
        var current =
            firstDayOfMonth.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.SUNDAY))
        while (current.isBefore(lastDayOfMonth) || current.isEqual(lastDayOfMonth)) {
            weeksInMonth.add(current)
            current = current.plusWeeks(1)
        }

        YearTitleOfFullCalendar(selectedDay.year)
        MonthTitleOfFullCalendar(
            selectedDay.month,
            previousMonthBtnClicked = { selectedDay = selectedDay.minusMonths(1) },
            nextMonthBtnClicked = { selectedDay = selectedDay.plusMonths(1) }
        )
        Spacer(modifier = Modifier.height(15.dp))

        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = TogedyTheme.colors.gray100,
                    shape = RoundedCornerShape(size = 10.dp)
                )
                .padding(18.dp)
        ) {
            Column {
                DayOfWeek()
                Spacer(modifier = Modifier.height(10.dp))
                Column {
                    for (startOfWeek in weeksInMonth) {
                        DayOfMonthRow(startOfWeek, selectedDay) {
                            selectedDay = it
                            onDaySelected(selectedDay)
                        }
                        Spacer(modifier = Modifier.height(22.dp))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MonthlyCalendarPreview(modifier: Modifier = Modifier) {
    MonthlyCalendar() { }
}