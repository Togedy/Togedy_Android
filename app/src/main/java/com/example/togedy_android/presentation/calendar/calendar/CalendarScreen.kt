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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.togedy_android.R
import com.example.togedy_android.presentation.calendar.addPersonalSchedule.AddPersonalScheduleViewModel
import com.example.togedy_android.presentation.calendar.component.CalendarAddButton
import com.example.togedy_android.presentation.calendar.component.CalendarFloatingBtn
import com.example.togedy_android.presentation.calendar.component.MonthlyCalendar
import java.time.LocalDate

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier,
    onCollegeScheduleBtnClicked: () -> Unit,
    onPersonalScheduleAddBtnClicked: () -> Unit,
    viewModel: AddPersonalScheduleViewModel = hiltViewModel()
) {
    var selectedDay by remember { mutableStateOf(LocalDate.now()) }
    var isFabExpanded by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 10.dp),
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
                            isFabExpanded = false
                            onCollegeScheduleBtnClicked()
                        }
                        CalendarAddButton(
                            imageVector = R.drawable.ic_calendar_plus,
                            description = "개인일정 추가하기"
                        ) {
                            isFabExpanded = false
                            onPersonalScheduleAddBtnClicked()
                        }
                    }
                    CalendarFloatingBtn(
                        onButtonClicked = {
                            isFabExpanded = !isFabExpanded
                        }
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.End
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                MonthlyCalendar() {
                    selectedDay = it
                }

                if (isFabExpanded) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0x80000000))
                            .clickable { isFabExpanded = false }
                    )
                }
            }
        }
    }
}