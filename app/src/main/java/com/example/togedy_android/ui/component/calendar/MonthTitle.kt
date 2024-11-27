package com.example.togedy_android.ui.component.calendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.R
import com.example.togedy_android.ui.theme.TogedyTheme
import java.time.Month

@Composable
fun MonthTitleOfFullCalendar(
    month: Month,
    previousMonthBtnClicked: () -> Unit,
    nextMonthBtnClicked: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_chevron_left),
            contentDescription = "이전버튼",
            tint = TogedyTheme.colors.gray500,
            modifier = Modifier.clickable { previousMonthBtnClicked() }
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = month.name,
            style = TogedyTheme.typography.headline2B
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_chevron_right),
            contentDescription = "다음버튼",
            tint = TogedyTheme.colors.gray500,
            modifier = Modifier.clickable { nextMonthBtnClicked() },
        )
    }
}

@Preview
@Composable
fun MonthTitleOfFullCalendarPreview(modifier: Modifier = Modifier) {
    MonthTitleOfFullCalendar(
        Month.SEPTEMBER,
        previousMonthBtnClicked = { },
        nextMonthBtnClicked = { }
    )
}