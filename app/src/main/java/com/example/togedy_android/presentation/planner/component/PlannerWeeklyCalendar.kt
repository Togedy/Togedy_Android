package com.example.togedy_android.presentation.planner.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
import com.example.togedy_android.core.design_system.component.GrayLine
import com.example.togedy_android.domain.type.PlanState
import com.example.togedy_android.presentation.calendar.component.DayOfWeek
import com.example.togedy_android.util.noRippleClickable

@Composable
fun PlannerWeeklyShortPlanner(
    selectedDay: LocalDate,
    onCalendarButtonClick: () -> Unit,
    onDaySelected: (LocalDate) -> Unit,
    onMoreButtonClicked: () -> Unit,
) {
    val today = LocalDate.now()
    val startOfWeek = today.minusDays(today.dayOfWeek.value.toLong() - 1)
    val endOfWeek = startOfWeek.plusDays(6)
    var selectedDay by remember { mutableStateOf(selectedDay) }

    Column(
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
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
                onDaySelected = {
                    selectedDay = it
                    onDaySelected(it)
                }
            )
        }

        Spacer(Modifier.height(16.dp))

        ShortPlanner(
            selectedDay = selectedDay,
            onMoreButtonClicked = onMoreButtonClicked
        )
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

@Composable
fun ShortPlanner(
    selectedDay: LocalDate,
    onMoreButtonClicked: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(2) {
            GrayLine()
            Spacer(Modifier.height(8.dp))
        }

        Text(
            text = selectedDay.toString(),
            style = TogedyTheme.typography.headline3B,
            color = TogedyTheme.colors.black,
        )

        Spacer(Modifier.height(8.dp))

        GrayLine()


        Spacer(Modifier.height(8.dp))

        repeat(3) {
            PlannerInputSection(
                planColor = null,
                planContent = "",
                planState = PlanState.NOT_STARTED,
                onPlanContentClicked = { /* 플랜 내용 변경 dialog */ },
                onPlanStateClicked = { /* 플랜 상태 변경 dialog */ }
            )
        }

        GrayLine()

        PlannerInputMoreSection(
            onMoreButtonClicked = onMoreButtonClicked
        )
    }
}

@Preview
@Composable
fun PlannerWeeklyShortPlannerPreview() {
    PlannerWeeklyShortPlanner(
        selectedDay = LocalDate.now(),
        onCalendarButtonClick = { },
        onDaySelected = { },
        onMoreButtonClicked = { }
    )
}