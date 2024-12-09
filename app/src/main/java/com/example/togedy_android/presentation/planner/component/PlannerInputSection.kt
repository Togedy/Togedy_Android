package com.example.togedy_android.presentation.planner.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
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
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.util.noRippleClickable

@Composable
fun PlannerInputSection(
    planColor: Color? = null,
    planContent: String = "",
    planState: PlanState = PlanState.NOT_STARTED,
    onPlanContentClicked: () -> Unit,
    onPlanStateClicked: () -> Unit,
) {
    val icon = when (planState) {
        PlanState.NOT_STARTED -> R.drawable.ic_planner_empty_box
        PlanState.COMPLETED -> R.drawable.ic_planner_completed
        PlanState.INCOMPLETE -> R.drawable.ic_planner_incompleted
        PlanState.POSTPONED -> R.drawable.ic_planner_postponed
        PlanState.NOT_EXECUTED -> R.drawable.ic_planner_not_excuted
    }

    Column {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (planColor == null) {
                Text(
                    text = stringResource(R.string.planner_no_plan),
                    style = TogedyTheme.typography.body2M,
                    color = TogedyTheme.colors.gray200,
                    modifier = Modifier.noRippleClickable(onPlanContentClicked)
                )
            } else {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_subject_color_circle),
                    contentDescription = "과목색상 버튼",
                    tint = planColor
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    text = planContent,
                    style = TogedyTheme.typography.body2M,
                    color = TogedyTheme.colors.black,
                    modifier = Modifier.noRippleClickable(onPlanContentClicked)
                )
            }

            Spacer(Modifier.weight(1f))

            Icon(
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = "플랜 상태 버튼",
                modifier = Modifier.noRippleClickable(onPlanStateClicked)
            )
        }

        HorizontalDivider(
            thickness = 1.dp,
            color = TogedyTheme.colors.gray100
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlannerInputSectionPreview() {
    PlannerInputSection(
        planColor = TogedyTheme.colors.color1,
        planContent = "1단원 공부하기",
        planState = PlanState.NOT_STARTED,
        onPlanContentClicked = { },
        onPlanStateClicked = { }
    )
}