package com.example.togedy_android.presentation.planner.plannerDetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun TimeTable(
    timeline: List<List<String>>
) {
    val highlightedMinutes = parseTimelineToMinutes(timeline)

    Box {
        Column {
            repeat(24) { hour ->
                Row {
                    repeat(60) { minute ->
                        val minuteOfDay = hour * 60 + minute
                        if (minute==0) {
                            Spacer(Modifier.width(1.dp))
                        }
                        Box(
                            modifier = Modifier
                                .width(2.dp)
                                .height(20.dp)
                                .background(
                                    if (highlightedMinutes.contains(minuteOfDay))
                                        TogedyTheme.colors.yellowMain
                                    else
                                        TogedyTheme.colors.white
                                )
                        )
                        if (minute%10==9) {
                            Spacer(Modifier.width(2.dp))
                        }
                    }
                }
            }
        }

        Column {
            repeat(24) { hour ->
                Row {
                    for (minute in 0 until 60 step 10) {
                        Box(
                            modifier = Modifier
                                .width(24.dp)
                                .height(20.dp)
                                .border(1.dp, TogedyTheme.colors.gray100)
                        )
                    }
                }
            }
        }
    }
}

fun parseTimelineToMinutes(timeline: List<List<String>>): List<Int> {
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    val highlightedMinutes = mutableSetOf<Int>()

    timeline.forEach { range ->
        val start = LocalTime.parse(range[0], timeFormatter)
        val end = LocalTime.parse(range[1], timeFormatter)

        var current = start
        while (current < end) {
            val minuteOfDay = current.hour * 60 + current.minute
            highlightedMinutes.add(minuteOfDay)
            current = current.plusMinutes(1)
        }
    }

    return highlightedMinutes.toList().sorted()
}
