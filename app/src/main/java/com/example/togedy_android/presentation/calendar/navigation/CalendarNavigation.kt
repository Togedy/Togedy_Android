package com.example.togedy_android.presentation.calendar.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.togedy_android.core.navigation.MainTabRoute
import com.example.togedy_android.presentation.calendar.CalendarScreen
import kotlinx.serialization.Serializable

fun NavHostController.navigateToCalendar(navOptions: NavOptions? = null) = navigate(Calendar)

fun NavGraphBuilder.calendarScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    composable<Calendar> {
        CalendarScreen(
            onCollegeScheduleBtnClicked = { },
            onPersonalScheduleAddBtnClicked = { },
        )
    }
}

@Serializable
data object Calendar : MainTabRoute