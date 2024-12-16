package com.example.togedy_android.presentation.calendar.calendar

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.togedy_android.R
import com.example.togedy_android.core.state.UiState
import com.example.togedy_android.presentation.calendar.calendar.state.CalendarDialogState
import com.example.togedy_android.presentation.calendar.calendar.state.CalendarUiState
import com.example.togedy_android.presentation.calendar.calendarDialog.CalendarDialogScreen
import com.example.togedy_android.presentation.calendar.component.CalendarAddButton
import com.example.togedy_android.presentation.calendar.component.CalendarFloatingBtn
import com.example.togedy_android.presentation.calendar.component.MonthlyCalendar
import java.time.LocalDate

@Composable
fun CalendarRoute(
    modifier: Modifier = Modifier,
    onCollegeScheduleBtnClicked: () -> Unit,
    onPersonalScheduleAddBtnClicked: () -> Unit,
    viewModel: CalendarViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val dialogState by viewModel.dialogState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.getCalendarHomeInformation(uiState.selectedDay)
    }

    CalendarScreen(
        modifier = modifier,
        uiState = uiState,
        dialogState = dialogState,
        onDaySelected = {
            viewModel.updateSelectedDay(it)
            viewModel.getCalendarHomeInformation(it)
            viewModel.updateDialogVisibility()
        },
        onFabButtonClicked = { viewModel.updateIsFabExpanded(it) },
        onCollegeScheduleBtnClicked = onCollegeScheduleBtnClicked,
        onPersonalScheduleAddBtnClicked = {
            onPersonalScheduleAddBtnClicked()
            viewModel.updateDialogVisibility()
        },
        onDismissRequest = { viewModel.updateDialogVisibility() }
    )
}

@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier,
    uiState: CalendarUiState,
    dialogState: CalendarDialogState,
    onDaySelected: (LocalDate) -> Unit,
    onFabButtonClicked: (Boolean) -> Unit,
    onCollegeScheduleBtnClicked: () -> Unit,
    onPersonalScheduleAddBtnClicked: () -> Unit,
    onDismissRequest: () -> Unit,
) {
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
                CalendarSuccessScreen(
                    modifier = modifier,
                    isFabExpanded = uiState.isFabExpanded,
                    selectedDay = uiState.selectedDay,
                    onDaySelected = { onDaySelected(it) },
                    onFabButtonClicked = { onFabButtonClicked(it) },
                    onCollegeScheduleBtnClicked = onCollegeScheduleBtnClicked,
                    onPersonalScheduleAddBtnClicked = onPersonalScheduleAddBtnClicked
                )
            }
        }
    }

    CalendarDialogScreen(
        dialogState =  dialogState,
        onDismissRequest = onDismissRequest,
        onPlanAddButtonClicked = onPersonalScheduleAddBtnClicked
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CalendarSuccessScreen(
    modifier: Modifier = Modifier,
    isFabExpanded: Boolean,
    selectedDay: LocalDate,
    onDaySelected: (LocalDate) -> Unit,
    onFabButtonClicked: (Boolean) -> Unit,
    onCollegeScheduleBtnClicked: () -> Unit,
    onPersonalScheduleAddBtnClicked: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Scaffold(
            floatingActionButton = {
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    if (isFabExpanded) {
                        CalendarAddButton(
                            imageVector = R.drawable.ic_building,
                            description = "대학별 일정 보기"
                        ) {
                            onFabButtonClicked(false)
                            onCollegeScheduleBtnClicked()
                        }
                        CalendarAddButton(
                            imageVector = R.drawable.ic_calendar_plus,
                            description = "개인일정 추가하기"
                        ) {
                            onFabButtonClicked(false)
                            onPersonalScheduleAddBtnClicked()
                        }
                    }
                    CalendarFloatingBtn(
                        onButtonClicked = {
                            onFabButtonClicked(!isFabExpanded)
                        }
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.End
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp)
                    .background(Color.White)
            ) {
                MonthlyCalendar(
                    selectedDay = selectedDay,
                    onDaySelected = { onDaySelected(it) }
                )

                if (isFabExpanded) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0x80000000))
                            .clickable { onFabButtonClicked(false) }
                    )
                }
            }
        }
    }
}