package com.example.togedy_android.presentation.calendar.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.togedy_android.core.design_system.theme.TogedyTheme

@Composable
fun DayOfWeek() {
    val dayOfWeekList = listOf("일", "월", "화", "수", "목", "금", "토")

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        dayOfWeekList.forEach { dayOfWeek ->
            val color = when (dayOfWeek) {
                "일" -> TogedyTheme.colors.red100
                "토" -> TogedyTheme.colors.darkblue200
                else -> TogedyTheme.colors.black
            }

            Text(
                text = dayOfWeek,
                style = TogedyTheme.typography.body2M,
                color = color,
                modifier = Modifier.weight(1f, fill = true),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun DayOfWeekPreview(modifier: Modifier = Modifier) {
    DayOfWeek()
}