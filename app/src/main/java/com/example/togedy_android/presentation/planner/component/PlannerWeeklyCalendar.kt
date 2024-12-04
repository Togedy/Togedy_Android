package com.example.togedy_android.presentation.planner.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.presentation.calendar.component.DayOfMonthRow
import java.time.LocalDate
import com.example.togedy_android.R
import com.example.togedy_android.presentation.calendar.component.DayOfWeek
import com.example.togedy_android.util.noRippleClickable

@Composable
fun PlannerWeeklyCalendar(
    selectedDay: LocalDate,
    onCalendarButtonClick: () -> Unit,
    onDaySelected: (LocalDate) -> Unit,
) {
    val today = LocalDate.now()
    val startOfWeek = today.minusDays(today.dayOfWeek.value.toLong() - 1)
    val endOfWeek = startOfWeek.plusDays(6)

    Column {
        PlannerWeeklyCalendarTitle(
            onCalendarButtonClick = onCalendarButtonClick
        )

        Spacer(Modifier.height(14.dp))

        DayOfWeek()

        Spacer(Modifier.height(20.dp))

        if (today in startOfWeek..endOfWeek) {
            DayOfMonthRow(
                startOfWeek = startOfWeek,
                selectedDay = selectedDay,
                onDaySelected = { onDaySelected(it) }
            )
        }
    }
}

@Composable
fun PlannerWeeklyCalendarTitle(
    onCalendarButtonClick: () -> Unit,
) {
    Row {
        Text(
            text = LocalDate.now().year.toString(),
            style = TogedyTheme.typography.body1R,
            color = TogedyTheme.colors.gray400
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = LocalDate.now().month.toString(),
            style = TogedyTheme.typography.body1B,
            color = TogedyTheme.colors.black
        )

        Spacer(Modifier.weight(1f))

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_calendar),
            contentDescription = stringResource(R.string.calendar_btn_calendar_description),
            tint = TogedyTheme.colors.gray400,
            modifier = Modifier.noRippleClickable(onCalendarButtonClick)
        )
    }
}

@Preview
@Composable
fun PlannerWeeklyCalendarPreview() {
    PlannerWeeklyCalendar(
        selectedDay = LocalDate.now(),
        onCalendarButtonClick = { },
        onDaySelected = { }
    )
}