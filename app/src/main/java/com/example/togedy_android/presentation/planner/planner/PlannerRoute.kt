package com.example.togedy_android.presentation.planner.planner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.togedy_android.core.design_system.component.GrayLine
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.core.design_system.theme.Togedy_AndroidTheme
import com.example.togedy_android.presentation.planner.component.PlannerDialogScreen
import com.example.togedy_android.presentation.planner.component.PlannerHomeTopBar
import com.example.togedy_android.presentation.planner.component.PlannerWeeklyShortPlanner
import com.example.togedy_android.presentation.planner.component.StudyGoalBlock
import com.example.togedy_android.presentation.planner.component.StudyTagBlock
import com.example.togedy_android.R
import com.example.togedy_android.core.state.UiState
import com.example.togedy_android.domain.model.planner.PlanItem
import com.example.togedy_android.domain.model.planner.StudyGoal
import com.example.togedy_android.domain.model.planner.StudyTag
import com.example.togedy_android.presentation.planner.planner.state.PlannerDialogState
import com.example.togedy_android.presentation.planner.planner.state.PlannerUiState
import com.example.togedy_android.presentation.planner.planner.type.PlannerDialogType
import com.example.togedy_android.util.noRippleClickable
import com.example.togedy_android.util.toHHhMmm
import com.example.togedy_android.util.toServerDataTime
import java.time.LocalDate

@Composable
fun PlannerRoute(
    modifier: Modifier = Modifier,
    onSettingButtonClick: () -> Unit,
    navigateToSetGoalTime: () -> Unit,
    navigateToEditGoalTime: (String) -> Unit,
    navigateToPlannerCalendar: () -> Unit,
    navigateToPlannerDetail: () -> Unit,
    viewModel: PlannerViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val dialogState by viewModel.dialogState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
//        viewModel.getPlannerHomeInformation()
        viewModel.getStudyGoal()
    }

    PlannerScreen(
        modifier = modifier,
        uiState = uiState,
        dialogState = dialogState,
        onDaySelected = viewModel::updateSelectedDay,
        onSettingButtonClick = onSettingButtonClick,
        navigateToSetGoalTime = navigateToSetGoalTime,
        navigateToEditGoalTime = { navigateToEditGoalTime(it) },
        navigateToPlannerCalendar = navigateToPlannerCalendar,
        navigateToPlannerDetail = navigateToPlannerDetail,
        onDismissRequest = viewModel::updateDialogVisibility,
        onAddStudyTagClicked = { viewModel.updateDialogVisibility(PlannerDialogType.ADD_SUBJECT) },
        onEditStudyTagClicked = {
            viewModel.updateStudyTag(it)
            viewModel.updateDialogVisibility(PlannerDialogType.EDIT_SUBJECT)
        },
        onPlanContentClicked = { todoId, planItem ->
            if (todoId == -1) {
                viewModel.updateDialogVisibility(PlannerDialogType.ADD_PLAN)
            } else {
                viewModel.updatePlanInfo(planItem)
                viewModel.updateDialogVisibility(PlannerDialogType.EDIT_PLAN)
            }
        },
        onPlanStateClicked = {
            viewModel.updateDialogVisibility(PlannerDialogType.EDIT_PLAN_STATE)
        }
    )
}

@Composable
fun PlannerScreen(
    modifier: Modifier = Modifier,
    uiState: PlannerUiState,
    dialogState: PlannerDialogState,
    onDaySelected: (LocalDate) -> Unit,
    onSettingButtonClick: () -> Unit,
    navigateToSetGoalTime: () -> Unit,
    navigateToEditGoalTime: (String) -> Unit,
    navigateToPlannerCalendar: () -> Unit,
    navigateToPlannerDetail: () -> Unit,
    onDismissRequest: (PlannerDialogType) -> Unit,
    onAddStudyTagClicked: () -> Unit,
    onEditStudyTagClicked: (StudyTag) -> Unit,
    onPlanContentClicked: (Int, PlanItem) -> Unit,
    onPlanStateClicked: (Int) -> Unit,
) {
    val scrollState = rememberScrollState()

    val studyGoal = when (uiState.studyGoalState) {
        is UiState.Success -> uiState.studyGoalState.data
        else -> StudyGoal(-1,"","",0)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = TogedyTheme.colors.white)
            .padding(top = 20.dp)
            .padding(horizontal = 20.dp)
            .verticalScroll(scrollState)
    ) {
        PlannerHomeTopBar(
            onSettingButtonClick = onSettingButtonClick
        )

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
                    PlannerSuccessScreen(
                        studyGoal = studyGoal,
                        selectedDay = uiState.selectedDay,
                        onDaySelected = onDaySelected,
                        planList = planList,
                        studyTagList = studyTagList,
                        navigateToSetGoalTime = navigateToSetGoalTime,
                        navigateToEditGoalTime = { navigateToEditGoalTime(it) },
                        navigateToPlannerCalendar = navigateToPlannerCalendar,
                        navigateToPlannerDetail = navigateToPlannerDetail,
                        onAddStudyTagClicked = onAddStudyTagClicked,
                        onEditStudyTagClicked = { onEditStudyTagClicked(it) },
                        onPlanContentClicked = { id, planItem ->
                            onPlanContentClicked(id, planItem)
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
        onStudyTagConfirm = { /* 공부태그 추가 api */ },
        onStudyTagEditConfirm = { /* 공부태그 수정 api */ },
        onPlanAddConfirm = { },
        onPlanEditConfirm = { }
    )
}

@Composable
fun PlannerSuccessScreen(
    studyGoal: StudyGoal,
    selectedDay: LocalDate,
    planList: List<PlanItem>,
    studyTagList: List<StudyTag>,
    onDaySelected: (LocalDate) -> Unit,
    navigateToSetGoalTime: () -> Unit,
    navigateToEditGoalTime: (String) -> Unit,
    navigateToPlannerCalendar: () -> Unit,
    navigateToPlannerDetail: () -> Unit,
    onAddStudyTagClicked: () -> Unit,
    onEditStudyTagClicked: (StudyTag) -> Unit,
    onPlanContentClicked: (Int, PlanItem) -> Unit,
    onPlanStateClicked: (Int) -> Unit,
) {
    Column {
        Spacer(Modifier.height(16.dp))

        StudyGoalBlock(
            studyGoal = studyGoal,
            navigateToSetGoalTime = navigateToSetGoalTime,
            navigateToEditGoalTime = {
                navigateToEditGoalTime(
                    studyGoal.targetTime.toServerDataTime().toString()
                )
            }
        )

        Spacer(Modifier.height(40.dp))

        GrayLine()

        Spacer(Modifier.height(20.dp))

        PlannerWeeklyShortPlanner(
            selectedDay = selectedDay,
            dayPlanItems = planList, //추후 수정 필요
            onCalendarButtonClick = navigateToPlannerCalendar,
            onDaySelected = {
                onDaySelected(selectedDay)
            },
            onMoreButtonClicked = navigateToPlannerDetail,
            onPlanContentClicked = { id, planItem ->
                onPlanContentClicked(id, planItem)
            },
            onPlanStateClicked = { onPlanStateClicked(it) }
        )

        Spacer(Modifier.height(24.dp))


        Column {
            Row(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Text(
                    text = "공부 태그",
                    style = TogedyTheme.typography.body1B,
                    color = TogedyTheme.colors.black
                )

                Spacer(Modifier.weight(1f))

                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_plus_circle),
                    contentDescription = "공부태그 추가 버튼",
                    tint = TogedyTheme.colors.yellowMain,
                    modifier = Modifier.noRippleClickable(onAddStudyTagClicked)
                )
            }

            if (studyTagList.isEmpty()) {
                Spacer(Modifier.height(20.dp))
                Text(
                    text = "공부 태그가 없습니다. 추가버튼으로 생성해보세요!",
                    style = TogedyTheme.typography.body2M,
                    color = TogedyTheme.colors.gray200
                )
            } else {
                Spacer(Modifier.height(16.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(studyTagList) { studyTag ->
                        StudyTagBlock(
                            studyTag,
                            onTagClicked = {
                                onEditStudyTagClicked(studyTag)
                            }
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun PlannerScreenPreview() {
    Togedy_AndroidTheme {
        PlannerRoute(
            onSettingButtonClick = { },
            navigateToEditGoalTime = { },
            navigateToSetGoalTime = { },
            navigateToPlannerCalendar = { },
            navigateToPlannerDetail = { }
        )
    }
}