package com.example.togedy_android.ui.screens.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.ui.component.BorderTextField
import com.example.togedy_android.ui.theme.TogedyTheme

@Composable
fun AddPersonalSchedule() {
    var scheduleName by remember { mutableStateOf("") }
    var memo by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TogedyTheme.colors.white)
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
    }
}


@Preview
@Composable
fun AddPersonalSchedulePreview(modifier: Modifier = Modifier) {
    AddPersonalSchedule()
}