package com.example.togedy_android.presentation.calendar.addPersonalSchedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.presentation.calendar.component.CalendarCategoryViewModel
import com.example.togedy_android.presentation.ui.component.BorderDateInput
import com.example.togedy_android.presentation.ui.component.BorderTextField
import com.example.togedy_android.presentation.ui.component.TopBarWithTextBtn
import com.example.togedy_android.presentation.ui.component.category.CategorySelection
import java.time.LocalDate

@Composable
fun AddPersonalScheduleScreen(
    modifier: Modifier = Modifier,
    closeButtonClicked: () -> Unit,
    addButtonClicked: () -> Unit
) {
    val calendarCategoryViewModel = CalendarCategoryViewModel()
    calendarCategoryViewModel.loadCategoryItems()
    var scheduleName by remember { mutableStateOf("") }
    var memo by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf<LocalDate?>(null) }
    var endDate by remember { mutableStateOf<LocalDate?>(null) }
    var isSingleDate by remember { mutableStateOf(true) }
    val buttonActive by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(TogedyTheme.colors.white)
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
    ) {
        TopBarWithTextBtn(
            leftButtonIcon = R.drawable.ic_x_close,
            title = "일정 추가하기",
            buttonText = "추가",
            buttonActive = buttonActive,
            onLeftButtonClicked = { closeButtonClicked() },
            onRightButtonClicked = { addButtonClicked() }
        )
        Spacer(modifier = Modifier.padding(top = 22.dp))

        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            BorderTextField(
                title = "일정 이름",
                value = scheduleName,
                onTextFieldChange = { scheduleName = it }
            )
            Spacer(modifier = Modifier.height(30.dp))

            BorderTextField(
                title = "메모",
                value = memo,
                onTextFieldChange = { memo = it }
            )
            Spacer(modifier = Modifier.height(30.dp))

            BorderDateInput(
                startDate = startDate,
                endDate = endDate,
                isSingleDate = isSingleDate,
                onIsSingleDateClicked = { isSingleDate = !isSingleDate },
                openCalendarDialog = { }
            )
            Spacer(modifier = Modifier.height(30.dp))

            CategorySelection(
                categories = calendarCategoryViewModel.categoryItems,
                onAddButtonClicked = { }
            )
        }
    }
}


@Preview
@Composable
fun AddPersonalSchedulePreview(modifier: Modifier = Modifier) {
    AddPersonalScheduleScreen(
        closeButtonClicked = {},
        addButtonClicked = {}
    )
}