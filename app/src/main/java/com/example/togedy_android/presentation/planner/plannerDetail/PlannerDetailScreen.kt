package com.example.togedy_android.presentation.planner.plannerDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.component.GrayLine
import com.example.togedy_android.core.design_system.component.TopBarBasicWithRightIcon
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.core.state.UiState
import com.example.togedy_android.presentation.planner.plannerDetail.component.DailyPlanList
import com.example.togedy_android.presentation.planner.plannerDetail.component.TimeTable
import com.example.togedy_android.presentation.planner.plannerDetail.state.PlannerDetailIntent
import java.time.LocalDate

@Composable
fun PlannerDetailScreen(
    selectedDay: LocalDate,
    dDay: String = "",
    onCloseButtonClicked: () -> Unit,
    onRightButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PlannerDetailViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(selectedDay) {
        viewModel.processIntent(PlannerDetailIntent.LoadDayPlan(selectedDay))
    }

    Column(
        modifier = modifier
            .background(color = TogedyTheme.colors.white)
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp)
    ) {
        TopBarBasicWithRightIcon(
            leftButtonIcon = R.drawable.ic_x_close,
            rightButtonIcon = R.drawable.ic_study_play,
            title = "플래너", //추후 변경 필요
            onLeftButtonClicked = onCloseButtonClicked,
            onRightButtonClicked = onRightButtonClicked,
        )

        Spacer(Modifier.height(18.dp))

        repeat(2) {
            GrayLine()
            Spacer(Modifier.height(8.dp))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 18.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = selectedDay.toString(),
                    style = TogedyTheme.typography.headline1B,
                    color = TogedyTheme.colors.black,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            if (dDay != "") {
                Text(
                    text = "D - $dDay",
                    style = TogedyTheme.typography.headline1B,
                    color = TogedyTheme.colors.gray400,
                    modifier = Modifier.width((24*6).dp),
                    textAlign = TextAlign.Center
                )
            } else {
                Text(
                    text = "set D-Day",
                    style = TogedyTheme.typography.headline2R,
                    color = TogedyTheme.colors.gray100,
                    modifier = Modifier.width((24*6).dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        when (val state = uiState.value.loadState) {
            is UiState.Loading -> { }

            is UiState.Success -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top
                ) {
                    DailyPlanList(
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(Modifier.width(10.dp))

                    TimeTable(
                        timeline = state.data.timeline,
                    )
                }
            }

            is UiState.Error -> { }

            UiState.Empty -> { }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlannerDetailScreenPreview(modifier: Modifier = Modifier) {
    PlannerDetailScreen(
        modifier = modifier,
        onCloseButtonClicked = { },
        onRightButtonClicked = { },
        selectedDay = LocalDate.now(),
        dDay = ""
    )
}