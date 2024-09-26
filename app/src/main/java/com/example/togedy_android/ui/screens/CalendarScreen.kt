package com.example.togedy_android.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.togedy_android.R
import com.example.togedy_android.ui.component.calendar.DateOfMonth
import com.example.togedy_android.ui.component.calendar.TitleDayOfWeek
import com.example.togedy_android.ui.component.calendar.TitleMonth
import com.example.togedy_android.ui.component.calendar.TitleYear
import com.example.togedy_android.ui.theme.Togedy_AndroidTheme
import java.time.LocalDate
import java.util.Date

@Composable
fun CalendarScreen(navController: NavController) {
    Togedy_AndroidTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Color.White,
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TitleYear()
                TitleMonth()
                Spacer(modifier = Modifier.height(16.dp))
                TitleDayOfWeek()
                DateOfMonth(
                    date = LocalDate.now()
                ) { }
            }
        }
    }
}