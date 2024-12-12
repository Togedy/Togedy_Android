package com.example.togedy_android.presentation.planner.plannerDetail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.togedy_android.core.design_system.component.GrayLine
import com.example.togedy_android.presentation.planner.component.PlannerInputSection

@Composable
fun DailyPlanList(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        GrayLine()

        repeat(12) {
            PlannerInputSection(
                onPlanTitleClicked = { },
                onPlanStatusClicked = { },
            )
        }
    }
}