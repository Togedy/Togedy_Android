package com.example.togedy_android.presentation.calendar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.domain.model.calendar.MonthlyScheduleItem
import com.example.togedy_android.presentation.calendar.calendarDialog.ScheduleBlock
import com.example.togedy_android.util.noRippleClickable
import com.example.togedy_android.util.toColor
import java.time.LocalDate

@Composable
fun DayOfMonthRow(
    type: String = "PLANNER",
    startOfWeek: LocalDate,
    selectedDay: LocalDate,
    onDaySelected: (LocalDate) -> Unit,
    weeklySchedule: List<MonthlyScheduleItem> = emptyList()
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 0 until 7) {
                val currentDay = startOfWeek.plusDays(i.toLong())
                val isSelected = currentDay == selectedDay
                val isToday = currentDay == LocalDate.now()

                val color = getColorForDay(isToday, i)
                val dayModifier = getModifierForDay(isToday, isSelected)
                    .noRippleClickable(
                        onClick = { onDaySelected(currentDay) }
                    )

                Row(
                    modifier = dayModifier,
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${currentDay.dayOfMonth}",
                        style = TogedyTheme.typography.body3M,
                        color = color,
                        modifier = Modifier
                            .weight(1f, fill = true)
                            .noRippleClickable { onDaySelected(currentDay) },
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        if (type == "CALENDAR") {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (i in 0 until 7) {
                    val currentDay = startOfWeek.plusDays(i.toLong())
                    val color = weeklySchedule[0].categoryColor.toColor()
                    val modifier = Modifier
                        .weight(1f, fill = true)
                        .padding(horizontal = 6.dp)
                        .background(
                            color = color ?: TogedyTheme.colors.gray100,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(vertical = 2.dp, horizontal = 8.dp)
                        .noRippleClickable { onDaySelected(currentDay) }
                    val time = "12:00"

                    ScheduleBlock(
                        modifier = modifier,
                        timeText = time,
                    )
                }
            }
        }
    }
}

@Composable
fun ScheduleBlock(
    modifier: Modifier,
    timeText: String
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = timeText,
            style = TogedyTheme.typography.overline,
            color = TogedyTheme.colors.gray700
        )
    }
}

@Composable
private fun getColorForDay(isToday: Boolean, dayIndex: Int) = when {
    isToday -> TogedyTheme.colors.white
    dayIndex == 0 -> TogedyTheme.colors.red100
    dayIndex == 6 -> TogedyTheme.colors.darkblue200
    else -> TogedyTheme.colors.black
}


@Composable
private fun getModifierForDay(isToday: Boolean, isSelected: Boolean): Modifier {
    val baseModifier = Modifier
        .width(22.dp)
        .height(22.dp)

    return when {
        isToday -> baseModifier
            .clip(RoundedCornerShape(50.dp))
            .background(TogedyTheme.colors.yellowMain)

        isSelected -> baseModifier
            .border(
                width = 2.dp,
                color = TogedyTheme.colors.yellow500,
                shape = RoundedCornerShape(50.dp)
            )

        else -> baseModifier
    }
}

@Preview
@Composable
fun DayOfMonthRowPreview() {
    DayOfMonthRow(
        selectedDay = LocalDate.now(),
        startOfWeek = LocalDate.now(),
        onDaySelected = {}
    )
}

@Preview
@Composable
fun DayOfMonthRowPreview2() {
    DayOfMonthRow(
        type = "CALENDAR",
        selectedDay = LocalDate.now(),
        startOfWeek = LocalDate.now(),
        onDaySelected = {}
    )
}