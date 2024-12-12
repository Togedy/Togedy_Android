package com.example.togedy_android.presentation.planner.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.domain.type.PlanState
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.component.GrayLine
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.util.noRippleClickable

@Composable
fun PlannerInputSection(
    studyTagColor: Color? = null,
    planTitle: String = "",
    status: PlanState = PlanState.NOT_STARTED,
    onPlanTitleClicked: () -> Unit,
    onPlanStatusClicked: () -> Unit,
) {
    val icon = when (status) {
        PlanState.NOT_STARTED -> R.drawable.ic_planner_empty_box
        PlanState.COMPLETED -> R.drawable.ic_planner_completed
        PlanState.INCOMPLETE -> R.drawable.ic_planner_incompleted
        PlanState.POSTPONED -> R.drawable.ic_planner_postponed
        PlanState.NOT_EXECUTED -> R.drawable.ic_planner_not_excuted
    }

    Column {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (studyTagColor == null) {
                Text(
                    text = stringResource(R.string.planner_no_plan),
                    style = TogedyTheme.typography.body2M,
                    color = TogedyTheme.colors.gray200,
                    modifier = Modifier.noRippleClickable(onPlanTitleClicked)
                )
            } else {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_subject_color_circle),
                    contentDescription = "과목색상 버튼",
                    tint = studyTagColor
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    text = planTitle,
                    style = TogedyTheme.typography.body2M,
                    color = TogedyTheme.colors.black,
                    modifier = Modifier
                        .weight(1f)
                        .noRippleClickable(onPlanTitleClicked)
                )
            }

            Icon(
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = "플랜 상태 버튼",
                tint = if (status != PlanState.NOT_STARTED) TogedyTheme.colors.red100 else TogedyTheme.colors.gray200,
                modifier = Modifier.noRippleClickable(onPlanStatusClicked)
            )
        }

        GrayLine()
    }
}

@Preview(showBackground = true)
@Composable
fun PlannerInputSectionPreview() {
    PlannerInputSection(
        studyTagColor = TogedyTheme.colors.color1,
        planTitle = "1단원 공부하기",
        status = PlanState.NOT_STARTED,
        onPlanTitleClicked = { },
        onPlanStatusClicked = { }
    )
}