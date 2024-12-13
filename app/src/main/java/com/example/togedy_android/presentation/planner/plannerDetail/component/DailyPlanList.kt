package com.example.togedy_android.presentation.planner.plannerDetail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.togedy_android.core.design_system.component.GrayLine
import com.example.togedy_android.domain.model.planner.PlanItem
import com.example.togedy_android.presentation.planner.component.PlannerInputSection
import com.example.togedy_android.util.toColor
import com.example.togedy_android.util.toPlanState
import kotlin.collections.List

@Composable
fun DailyPlanList(
    modifier: Modifier = Modifier,
    dayPlanItems: List<PlanItem>,
    onPlanContentClicked: (Int, PlanItem) -> Unit,
    onPlanStateClicked: (Int) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        GrayLine()

        val placeholdersNeeded = 12 - dayPlanItems.size

        for (index in dayPlanItems.indices) {
            val dayPlan = dayPlanItems[index]

            PlannerInputSection(
                studyTagColor = dayPlan.subjectColor.toColor(),
                planTitle = dayPlan.title,
                status = dayPlan.status.toPlanState(),
                onPlanTitleClicked = { onPlanContentClicked(dayPlan.todoID, dayPlan) },
                onPlanStatusClicked = { onPlanStateClicked(dayPlan.todoID) }
            )
        }

        repeat(placeholdersNeeded) {
            PlannerInputSection(
                onPlanTitleClicked = { onPlanContentClicked(-1, PlanItem(todoID = -1, subjectColor = "", title = "", status = "")) },
                onPlanStatusClicked = { /* 플랜이 입력 안되어 있을 때 비활성화 */ }
            )
        }
    }
}