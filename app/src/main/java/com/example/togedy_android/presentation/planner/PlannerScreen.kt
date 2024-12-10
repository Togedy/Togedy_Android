package com.example.togedy_android.presentation.planner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.core.design_system.theme.Togedy_AndroidTheme
import com.example.togedy_android.core.design_system.theme.gray100
import com.example.togedy_android.presentation.planner.component.PlannerHomeTopBar
import com.example.togedy_android.presentation.planner.component.PlannerWeeklyShortPlanner
import com.example.togedy_android.presentation.planner.component.TodaysGoal
import java.time.LocalDate

@Composable
fun PlannerScreen(
    modifier: Modifier = Modifier,
    onSettingButtonClick: () -> Unit,
    navigateToSetGoalTime: () -> Unit,
    navigateToEditGoalTime: () -> Unit,
) {
    var selectedDay: LocalDate by remember { mutableStateOf(LocalDate.now()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = TogedyTheme.colors.white)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(20.dp))

        PlannerHomeTopBar(
            onSettingButtonClick = onSettingButtonClick
        )

        Spacer(Modifier.height(16.dp))

        TodaysGoal(
            goalTime = "00:00",
            percentage = 90,
            navigateToSetGoalTime = navigateToSetGoalTime,
            navigateToEditGoalTime = navigateToEditGoalTime
        )

        Spacer(Modifier.height(40.dp))

        HorizontalDivider(
            thickness = 1.dp,
            color = gray100
        )

        Spacer(Modifier.height(20.dp))

        Column(
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            PlannerWeeklyShortPlanner(
                selectedDay = selectedDay,
                onCalendarButtonClick = { },
                onDaySelected = { selectedDay = it }
            )

        }

    }
}

@Preview
@Composable
fun PlannerScreenPreview() {
    Togedy_AndroidTheme {
        PlannerScreen(
            onSettingButtonClick = { },
            navigateToEditGoalTime = { },
            navigateToSetGoalTime = { }
        )
    }
}