package com.example.togedy_android.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.togedy_android.ui.component.calendar.MonthlyCalendar
import com.example.togedy_android.ui.screens.calendar.AddScheduleBtn
import com.example.togedy_android.ui.theme.Togedy_AndroidTheme
import java.time.LocalDate

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CalendarScreen(navController: NavController) {
    var selectedDay by remember { mutableStateOf(LocalDate.now()) }

    Togedy_AndroidTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            color = colorScheme.background,
        ) {
            Scaffold(
                floatingActionButton = {
                    AddScheduleBtn(
                        onButtonClicked = { }
                    )
                },
                floatingActionButtonPosition = FabPosition.End
            ) {
                MonthlyCalendar() {
                    selectedDay = it
                    Log.d("chrin", "CalendarScreen: MonthlyCalendar selectedDay = $selectedDay")
                }
            }
        }
    }
}