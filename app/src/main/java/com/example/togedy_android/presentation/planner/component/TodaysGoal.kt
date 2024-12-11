package com.example.togedy_android.presentation.planner.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.core.design_system.theme.Togedy_AndroidTheme
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.component.TogedyButtonWithBorder
import com.example.togedy_android.util.noRippleClickable

@Composable
fun TodaysGoal(
    goalTime: String,
    percentage: Int,
    navigateToSetGoalTime: () -> Unit,
    navigateToEditGoalTime: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                spotColor = Color(0xFF000000),
                ambientColor = Color(0x1A000000)
            )
            .background(color = TogedyTheme.colors.white, shape = RoundedCornerShape(size = 10.dp))
            .padding(top = 12.dp, bottom = 18.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.planner_today_goal_title),
                style = TogedyTheme.typography.body2B,
                color = TogedyTheme.colors.black,
                modifier = Modifier.padding(vertical = 2.dp)
            )

            /* 설정된 시간이 없을 때 */
            if (goalTime != "00:00") {
                Spacer(Modifier.width(8.dp))

                Box(
                    modifier = Modifier
                        .background(
                            color = TogedyTheme.colors.yellowMain,
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "$percentage%",
                        style = TogedyTheme.typography.body2B,
                        color = TogedyTheme.colors.white
                    )
                }
            }
        }

        if (goalTime == "00:00") {
            NoGoal(navigateToSetGoalTime = navigateToSetGoalTime)
        } else {
            ShowGoalStatus(
                percentage = percentage,
                navigateToEditGoalTime = { navigateToEditGoalTime() }
            )
        }
    }
}

@Composable
fun ShowGoalStatus(
    percentage: Int,
    navigateToEditGoalTime: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 15.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val icon = if (percentage == 100) R.drawable.ic_full_star else R.drawable.ic_empty_star

            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        color = TogedyTheme.colors.gray100,
                        shape = RoundedCornerShape(size = 10.dp)
                    )
                    .height(14.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp)
                ) {
                    val progress = percentage / 100f
                    Box(
                        modifier = Modifier
                            .weight(progress)
                            .background(
                                color = TogedyTheme.colors.yellowMain,
                                shape = RoundedCornerShape(size = 10.dp)
                            )
                            .height(10.dp)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1 - progress)
                            .height(10.dp)
                    )
                }
            }

            Spacer(Modifier.width(4.dp))

            Icon(
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = stringResource(R.string.btn_star_description),
                tint = Color.Unspecified
            )
        }

        Text(
            text = "3h 20m",
            style = TogedyTheme.typography.body3M,
            color = TogedyTheme.colors.gray500,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
                .padding(start = 20.dp)
        )

        Spacer(Modifier.height(18.dp))

        if (percentage == 100) {
            Text(
                text = stringResource(R.string.planner_approve_goal_mention),
                style = TogedyTheme.typography.body3B,
                color = TogedyTheme.colors.gray600,
                textAlign = TextAlign.Center
            )
        } else {
            TogedyButtonWithBorder(
                buttonText = stringResource(R.string.planner_edit_goal_button),
                isActivated = false,
                modifier = Modifier.noRippleClickable(navigateToEditGoalTime)
            )
        }
    }
}

@Composable
fun NoGoal(
    navigateToSetGoalTime: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.planner_no_goal_mention),
            style = TogedyTheme.typography.body3M,
            color = TogedyTheme.colors.gray200,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(18.dp))

        TogedyButtonWithBorder(
            buttonText = stringResource(R.string.planner_set_goal_button),
            isActivated = true,
            modifier = Modifier.noRippleClickable(navigateToSetGoalTime)
        )
    }
}

@Preview
@Composable
fun TodaysGoalPreview() {
    Togedy_AndroidTheme {
        TodaysGoal(
            goalTime = "01:00",
            percentage = 50,
            navigateToSetGoalTime = { },
            navigateToEditGoalTime = { }
        )
    }
}