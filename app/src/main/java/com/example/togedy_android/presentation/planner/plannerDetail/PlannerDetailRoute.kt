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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.component.GrayLine
import com.example.togedy_android.core.design_system.component.TopBarBasic
import com.example.togedy_android.core.design_system.component.TopBarBasicWithRightIcon
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.core.state.UiState
import com.example.togedy_android.domain.model.planner.NewStudyPlan
import com.example.togedy_android.domain.model.planner.PlanItem
import com.example.togedy_android.domain.model.planner.StudyPlanItem
import com.example.togedy_android.presentation.planner.component.PlannerDialogScreen
import com.example.togedy_android.presentation.planner.planner.state.PlannerDialogState
import com.example.togedy_android.presentation.planner.planner.type.PlannerDialogType
import com.example.togedy_android.presentation.planner.plannerDetail.component.DailyPlanList
import com.example.togedy_android.presentation.planner.plannerDetail.component.TimeTable
import com.example.togedy_android.presentation.planner.plannerDetail.state.PlannerDetailUiState
import java.time.LocalDate

@Composable
fun PlannerDetailRoute(
    selectedDay: LocalDate,
    dDay: String = "",
    onCloseButtonClicked: () -> Unit,
    onRightButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PlannerDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val dialogState by viewModel.dialogState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.getDayPlanInformation(selectedDay)
    }

    PlannerDetailScreen(
        modifier = modifier,
        uiState = uiState,
        selectedDay = selectedDay,
        dDay = dDay,
        dialogState = dialogState,
        onCloseButtonClicked = onCloseButtonClicked,
        onRightButtonClicked = onRightButtonClicked,
        onDismissRequest = viewModel::updateDialogVisibility,
        onPlanContentClicked = { todoId, planItem ->
            if (todoId == -1) {
                viewModel.updateDialogVisibility(PlannerDialogType.ADD_PLAN)
            } else {
//                viewModel.updatePlanInfo(planItem)
                viewModel.updateDialogVisibility(PlannerDialogType.EDIT_PLAN)
            }
        },
        onPlanStateClicked = {
            viewModel.updateDialogVisibility(PlannerDialogType.EDIT_PLAN_STATE)
        },
        onPlanAddConfirm = {
            viewModel.postStudyPlan(it)
        }
    )
}

@Composable
fun PlannerDetailScreen(
    modifier: Modifier,
    uiState: PlannerDetailUiState,
    selectedDay: LocalDate,
    dDay: String,
    dialogState: PlannerDialogState,
    onCloseButtonClicked: () -> Unit,
    onRightButtonClicked: () -> Unit,
    onDismissRequest: (PlannerDialogType) -> Unit,
    onPlanContentClicked: (Int, StudyPlanItem?) -> Unit,
    onPlanStateClicked: (Int) -> Unit,
    onPlanAddConfirm: (NewStudyPlan) -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .background(color = TogedyTheme.colors.white)
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp)
            .verticalScroll(scrollState)
    ) {
        if (selectedDay == LocalDate.now()) {
            TopBarBasicWithRightIcon(
                leftButtonIcon = R.drawable.ic_x_close,
                rightButtonIcon = R.drawable.ic_study_play,
                title = "플래너", //추후 변경 필요
                onLeftButtonClicked = onCloseButtonClicked,
                onRightButtonClicked = onRightButtonClicked,
            )
        } else {
            TopBarBasic(
                leftButtonIcon = R.drawable.ic_x_close,
                title = "플래너",
                onLeftButtonClicked = onCloseButtonClicked,
            )
        }

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
                    modifier = Modifier.width((24 * 6).dp),
                    textAlign = TextAlign.Center
                )
            } else {
                Text(
                    text = "set D-Day",
                    style = TogedyTheme.typography.headline2R,
                    color = TogedyTheme.colors.gray100,
                    modifier = Modifier.width((24 * 6).dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        when (uiState.loadState) {
            is UiState.Loading -> {
                // 로딩 중 UI 표시 (구현 예정 X)
            }

            is UiState.Empty -> {
                // 빈 UI 표시 (구현 예정 X)
            }

            is UiState.Error -> {
                // 에러 UI 표시 (구현 예정 X)
            }

            is UiState.Success -> {
                with(uiState.loadState.data) {
                    PlannerDetailSuccessScreen(
                        dayPlanItems = planList,
                        onPlanContentClicked = { id, planItem ->
                            if (planItem != null) {
                                onPlanContentClicked(id, planItem)
                            }
                        },
                        onPlanStateClicked = { onPlanStateClicked(it) }
                    )
                }
            }
        }
    }

    PlannerDialogScreen(
        dialogState = dialogState,
        onDismissRequest = onDismissRequest,
        onStudyTagConfirm = { /* 이 화면에서는 안 쓰임 */ },
        onStudyTagEditConfirm = { /* 이 화면에서는 안 쓰임 */ },
        onPlanAddConfirm = { onPlanAddConfirm(it) },
        onPlanEditConfirm = { }
    )
}

@Composable
fun PlannerDetailSuccessScreen(
    dayPlanItems: List<StudyPlanItem>,
    onPlanContentClicked: (Int, StudyPlanItem?) -> Unit,
    onPlanStateClicked: (Int) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        DailyPlanList(
            modifier = Modifier.weight(1f),
            dayPlanItems = dayPlanItems,
            onPlanContentClicked = { todoId, planItem ->
                if (planItem != null) {
                    onPlanContentClicked(todoId, planItem)
                }
            },
            onPlanStateClicked = { onPlanStateClicked(it) },
        )

        Spacer(Modifier.width(10.dp))

        TimeTable(
            timeline = if (dayPlanItems.isEmpty()) emptyList() else dayPlanItems[0].studyRecords,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlannerDetailScreenPreview(modifier: Modifier = Modifier) {
    PlannerDetailRoute(
        modifier = modifier,
        onCloseButtonClicked = { },
        onRightButtonClicked = { },
        selectedDay = LocalDate.now(),
        dDay = ""
    )
}