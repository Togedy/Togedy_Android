package com.example.togedy_android.presentation.planner.setGoalTime

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.component.BorderTextField
import com.example.togedy_android.core.design_system.component.TopBarWithTextBtn
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.core.design_system.theme.Togedy_AndroidTheme

@Composable
fun SetGoalTimeScreen(
    targetTime: String? = "00:00",
    onCloseButtonClicked: () -> Unit,
    onSetButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SetGoalTimeViewModel = hiltViewModel(),
) {
    var inputGoalTime by remember { mutableStateOf(targetTime.toString()) }
    var isSetButtonActivated = viewModel.isSetButtonActivated.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 20.dp)
            .padding(horizontal = 20.dp)
    ) {
        TopBarWithTextBtn(
            leftButtonIcon = R.drawable.ic_x_close,
            title = stringResource(R.string.planner_today_goal),
            buttonText = "설정",
            buttonActive = isSetButtonActivated.value,
            onLeftButtonClicked = onCloseButtonClicked,
            onRightButtonClicked = {
                if (isSetButtonActivated.value) {
                    viewModel.postGoalTime(inputGoalTime)
                    onSetButtonClicked()
                }
            }
        )

        Spacer(Modifier.height(30.dp))

        BorderTextField(
            title = stringResource(R.string.planner_today_goal),
            value = inputGoalTime,
            onTextFieldChange = {
                inputGoalTime = it
                viewModel.updateSetButtonActivation(it)
            },
        )

        Spacer(Modifier.height(18.dp))

        Row {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_alert_triangle),
                contentDescription = stringResource(R.string.btn_settings_description),
                tint = TogedyTheme.colors.gray300
            )

            Spacer(Modifier.width(10.dp))

            Text(
                text = stringResource(R.string.planner_goal_time_notification),
                style = TogedyTheme.typography.body2M,
                color = TogedyTheme.colors.gray300
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SetGoalTimeScreenPreview() {
    Togedy_AndroidTheme {
        SetGoalTimeScreen(
            onCloseButtonClicked = { },
            onSetButtonClicked = { }
        )
    }
}