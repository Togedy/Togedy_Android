package com.example.togedy_android.ui.component.calendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.ui.theme.TogedyTheme
import java.time.LocalDate

@Composable
fun DayOfMonthRow(
    startOfWeek: LocalDate,
    daySelected: (LocalDate) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until 7) {
            val currentDay = startOfWeek.plusDays(i.toLong())

            val color = when (i) {
                0 -> TogedyTheme.colors.red100
                6 -> TogedyTheme.colors.darkblue200
                else -> TogedyTheme.colors.black
            }

            Text(
                text = "${currentDay.dayOfMonth}",
                style = TogedyTheme.typography.body3M,
                color = color,
                modifier = Modifier
                    .weight(1f, fill = true)
                    .clickable { daySelected(currentDay) },
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun DayOfMonthRowPreview(modifier: Modifier = Modifier) {
    DayOfMonthRow(LocalDate.now()) { }
}