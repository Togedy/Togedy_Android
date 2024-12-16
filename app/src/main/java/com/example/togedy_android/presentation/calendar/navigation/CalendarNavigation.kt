package com.example.togedy_android.presentation.calendar.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.togedy_android.core.navigation.MainTabRoute
import com.example.togedy_android.core.navigation.Route
import com.example.togedy_android.presentation.calendar.CalendarScreen
import com.example.togedy_android.presentation.calendar.addPersonalSchedule.AddPersonalScheduleScreen
import com.example.togedy_android.presentation.calendar.univDetail.UnivDetailScreen
import kotlinx.serialization.Serializable

fun NavHostController.navigateToCalendar(navOptions: NavOptions? = null) = navigate(Calendar)

fun NavHostController.navigateToAddPersonalSchedule(navOptions: NavOptions? = null) =
    navigate(AddPersonalSchedule)

fun NavHostController.navigateToUnivDetail(navOptions: NavOptions? = null) =
    navigate(UnivDetail)

fun NavGraphBuilder.calendarScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    composable<Calendar> {
        CalendarScreen(
            modifier = modifier,
            onCollegeScheduleBtnClicked = { navController.navigateToUnivDetail() },
            onPersonalScheduleAddBtnClicked = { navController.navigateToAddPersonalSchedule() },
        )
    }

    composable<AddPersonalSchedule> {
        AddPersonalScheduleScreen(
            modifier = modifier,
            closeButtonClicked = { navController.popBackStack() },
            addButtonClicked = { }
        )
    }

    composable<UnivDetail> {
        UnivDetailScreen(
            modifier = modifier
        )
    }
}

@Serializable
data object Calendar : MainTabRoute

@Serializable
data object AddPersonalSchedule : Route

@Serializable
data object UnivDetail : Route