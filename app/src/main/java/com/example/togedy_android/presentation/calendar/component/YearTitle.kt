package com.example.togedy_android.presentation.calendar.component

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import java.time.LocalDate

@Composable
fun YearTitleOfFullCalendar(
    year: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = year.toString(),
            style = TogedyTheme.typography.body1R,
            color = TogedyTheme.colors.gray700
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            painter = painterResource(R.drawable.ic_chevron_selector_vertical),
            contentDescription = "연도 선택 버튼",
            modifier = Modifier.clickable {
                //연도 선택 창
            }
        )
    }
}

@Preview
@Composable
fun YearTitleOfFullCalendarPreview(modifier: Modifier = Modifier) {
    YearTitleOfFullCalendar(LocalDate.now().year)
}