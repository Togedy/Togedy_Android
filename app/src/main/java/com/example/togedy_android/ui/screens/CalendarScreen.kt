package com.example.togedy_android.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.togedy_android.ui.component.calendar.MonthlyCalendar
import com.example.togedy_android.ui.theme.Togedy_AndroidTheme

@Composable
fun CalendarScreen(navController: NavController) {
    Togedy_AndroidTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            color = colorScheme.background
        ) {
            MonthlyCalendar()
        }
    }
}