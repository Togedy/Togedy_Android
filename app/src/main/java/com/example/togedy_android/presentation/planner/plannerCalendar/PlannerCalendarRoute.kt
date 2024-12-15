package com.example.togedy_android.presentation.planner.plannerCalendar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.component.TopBarBasic
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.core.design_system.theme.white
import com.example.togedy_android.core.state.UiState
import com.example.togedy_android.domain.model.planner.NewStudyPlan
import com.example.togedy_android.domain.model.planner.PlanItem
import com.example.togedy_android.domain.model.planner.StudyPlanItem
import com.example.togedy_android.presentation.calendar.component.DayOfMonthRow
import com.example.togedy_android.presentation.calendar.component.DayOfWeek
import com.example.togedy_android.presentation.calendar.component.MonthTitleOfFullCalendar
import com.example.togedy_android.presentation.calendar.component.YearTitleOfFullCalendar
import com.example.togedy_android.presentation.planner.component.PlannerDialogScreen
import com.example.togedy_android.presentation.planner.component.ShortPlanner
import com.example.togedy_android.presentation.planner.planner.PlannerViewModel
import com.example.togedy_android.presentation.planner.planner.state.PlannerDialogState
import com.example.togedy_android.presentation.planner.planner.state.PlannerUiState
import com.example.togedy_android.presentation.planner.planner.type.PlannerDialogType
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

@Composable
fun PlannerCalendarRoute(
    onCloseButtonClicked: () -> Unit,
    navigateToPlannerDetail: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PlannerViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val dialogState by viewModel.dialogState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.getPlannerHomeInformation(uiState.selectedDay)
    }

    PlannerCalendarScreen(
        modifier = modifier,
        uiState = uiState,
        dialogState = dialogState,
        onDaySelected = {
            viewModel::updateSelectedDay
            viewModel.getPlannerHomeInformation(it)
        },
        onCloseButtonClicked = onCloseButtonClicked,
        navigateToPlannerDetail = navigateToPlannerDetail,
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
fun PlannerCalendarScreen(
    modifier: Modifier = Modifier,
    uiState: PlannerUiState,
    dialogState: PlannerDialogState,
    onDaySelected: (LocalDate) -> Unit,
    onCloseButtonClicked: () -> Unit,
    navigateToPlannerDetail: () -> Unit,
    onDismissRequest: (PlannerDialogType) -> Unit,
    onPlanContentClicked: (Int, StudyPlanItem) -> Unit,
    onPlanStateClicked: (Int) -> Unit,
    onPlanAddConfirm: (NewStudyPlan) -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .background(color = TogedyTheme.colors.white)
            .fillMaxSize()
            .padding(top = 20.dp)
            .padding(horizontal = 10.dp)
            .verticalScroll(scrollState)
    ) {
        TopBarBasic(
            leftButtonIcon = R.drawable.ic_x_close,
            title = "",
            onLeftButtonClicked = onCloseButtonClicked,
            modifier = Modifier.padding(horizontal = 10.dp)
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
                    PlannerCalendarSuccessScreen(
                        selectedDay = uiState.selectedDay,
                        onDaySelected = onDaySelected,
                        dayPlanItems = studyPlanList,
                        navigateToPlannerDetail = navigateToPlannerDetail,
                        onPlanContentClicked = { id, planItem ->
                            onPlanContentClicked(id, planItem)
                        },
                        onPlanStateClicked = { onPlanStateClicked(it) },
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
        onPlanAddConfirm = { onPlanAddConfirm(it) },
        onPlanEditConfirm = { }
    )
}

@Composable
fun PlannerCalendarSuccessScreen(
    selectedDay: LocalDate,
    onDaySelected: (LocalDate) -> Unit,
    dayPlanItems: List<StudyPlanItem>,
    navigateToPlannerDetail: () -> Unit,
    onPlanContentClicked: (Int, StudyPlanItem) -> Unit,
    onPlanStateClicked: (Int) -> Unit,
) {
    var selectedDay by remember { mutableStateOf(selectedDay) }

    Column {
        PlannerMonthlyCalendar(
            onDaySelected = {
                selectedDay = it
                onDaySelected(it)
            }
        )

        Spacer(Modifier.height(38.dp))

        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            ShortPlanner(
                selectedDay = selectedDay,
                dayPlanItems = dayPlanItems,
                onMoreButtonClicked = navigateToPlannerDetail,
                onPlanContentClicked = { todoId, planItem ->
                    if (planItem != null) {
                        onPlanContentClicked(todoId, planItem)
                    }
                },
                onPlanStateClicked = { onPlanStateClicked(it) }
            )
        }
    }
}

@Composable
fun PlannerMonthlyCalendar(
    onDaySelected: (LocalDate) -> Unit
) {
    Column(
        modifier = Modifier.background(white)
    ) {
        val today = LocalDate.now()
        var selectedDay by remember { mutableStateOf(today) }

        val firstDayOfMonth = selectedDay.withDayOfMonth(1)
        val lastDayOfMonth =
            LocalDate.of(selectedDay.year, selectedDay.month, selectedDay.lengthOfMonth())
        val weeksInMonth = mutableListOf<LocalDate>()
        var current =
            firstDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
        while (current.isBefore(lastDayOfMonth) || current.isEqual(lastDayOfMonth)) {
            weeksInMonth.add(current)
            current = current.plusWeeks(1)
        }

        YearTitleOfFullCalendar(selectedDay.year)
        MonthTitleOfFullCalendar(
            selectedDay.month,
            previousMonthBtnClicked = { selectedDay = selectedDay.minusMonths(1) },
            nextMonthBtnClicked = { selectedDay = selectedDay.plusMonths(1) }
        )
        Spacer(modifier = Modifier.height(15.dp))

        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = TogedyTheme.colors.gray100,
                    shape = RoundedCornerShape(size = 10.dp)
                )
                .padding(18.dp)
        ) {
            Column {
                DayOfWeek()
                Spacer(modifier = Modifier.height(10.dp))
                Column {
                    for (startOfWeek in weeksInMonth) {
                        DayOfMonthRow(startOfWeek, selectedDay) {
                            selectedDay = it
                            onDaySelected(selectedDay)
                        }
                        Spacer(modifier = Modifier.height(22.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlannerCalendarScreenPreview() {
    PlannerCalendarRoute(
        onCloseButtonClicked = { },
        navigateToPlannerDetail = { }
    )
}