package com.example.togedy_android.ui.component.calendar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.togedy_android.R
import java.time.LocalDate
import java.time.Month

@Composable
fun DateOfMonth(
    date: LocalDate,
    clicked: (LocalDate) -> Unit,
) {
    val startOfMonth = date.withDayOfMonth(1)
    val endOfMonth = date.withDayOfMonth(date.lengthOfMonth())

    val firstDayOfWeek = startOfMonth.minusDays((startOfMonth.dayOfWeek.value % 7).toLong())
    val lastDayOfWeek = endOfMonth.plusDays((6 - endOfMonth.dayOfWeek.value % 7).toLong())

    val weeks = mutableListOf<List<LocalDate>>()

    var currentDay = firstDayOfWeek
    while (currentDay <= lastDayOfWeek) {
        val week = (0..6).map {
            currentDay.plusDays(it.toLong())
        }
        weeks.add(week)
        currentDay = currentDay.plusWeeks(1)
    }

    var clickedDate by remember { mutableStateOf<LocalDate?>(null) }

    Column {
        weeks.forEach { week ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                week.forEach { day ->
                    val isInCurrentMonth = day.month == date.month
                    val isSelected = clickedDate == day && isInCurrentMonth
                    val textColor = if (isInCurrentMonth) {
                        determineTextColor(LocalDate.now(), day.dayOfMonth, day.dayOfWeek.value, day.month)
                    } else {
                        androidx.compose.ui.graphics.Color.Gray
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .width(32.dp)
                                .then(
                                    if (isInCurrentMonth) {
                                        Modifier.clickable {
                                            clickedDate = day
                                            clicked(clickedDate!!)
                                        }
                                    } else {
                                        Modifier
                                    }
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            DrawCircle(
                                today = LocalDate.now(),
                                currentDate = day.dayOfMonth,
                                isSelected = isSelected,
                                month = day.month
                            )
                            Text(
                                text = day.dayOfMonth.toString(),
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                                    color = textColor
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))

                    }
                }
            }
            Spacer(modifier = Modifier.height(46.dp))
        }
    }
}

@Composable
fun determineTextColor(today: LocalDate, currentDate: Int, dayIndex: Int, month: Month?): Color {
    return when {
        today.dayOfMonth == currentDate && today.month == month -> colorResource(id = R.color.white)
        dayIndex == 0 -> colorResource(id = R.color.error)
        dayIndex == 6 -> colorResource(id = R.color.main_blue)
        else -> colorResource(id = R.color.black)
    }
}

@Composable
fun DrawCircle(today: LocalDate, currentDate: Int, isSelected: Boolean, month: Month?) {
    if (today.dayOfMonth == currentDate && today.month == month) {
        TodayBackgroundCircle()
    } else {
        val backgroundColor = if (isSelected) R.color.main_blue else R.color.white
        BackgroundCircle(color = backgroundColor)
    }
}

@Composable
fun BackgroundCircle(color: Int) {
    val backgroundColor = colorResource(id = color)
    Canvas(
        modifier = Modifier.size(24.dp)
    ) {
        drawCircle(
            color = backgroundColor,
            radius = size.minDimension / 2,
            style = Stroke(width = 5f)
        )
    }
}

@Composable
fun TodayBackgroundCircle() {
    val backgroundColor = colorResource(id = R.color.main_blue)
    Canvas(
        modifier = Modifier.size(24.dp)
    ) {
        drawCircle(
            color = backgroundColor,
            radius = size.minDimension / 2
        )
    }
}