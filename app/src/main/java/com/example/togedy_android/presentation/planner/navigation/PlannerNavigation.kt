package com.example.togedy_android.presentation.planner.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.togedy_android.core.navigation.MainTabRoute
import com.example.togedy_android.core.navigation.Route
import com.example.togedy_android.presentation.planner.PlannerScreen
import com.example.togedy_android.presentation.planner.plannerCalendar.PlannerCalendarScreen
import com.example.togedy_android.presentation.planner.plannerDetail.PlannerDetailRoute
import com.example.togedy_android.presentation.planner.setGoalTime.SetGoalTimeScreen
import kotlinx.serialization.Serializable
import java.time.LocalDate

fun NavHostController.navigateToPlanner(navOptions: NavOptions? = null) =
    navigate(Planner)

fun NavHostController.navigateToSetGoalTime(navOptions: NavOptions? = null) =
    navigate(SetGoalTime)

fun NavHostController.navigateToPlannerCalendar(navOptions: NavOptions? = null) =
    navigate(PlannerCalendar)

fun NavHostController.navigateToPlannerDetial(navOptions: NavOptions? = null) =
    navigate(PlannerDetail)

fun NavGraphBuilder.plannerScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    composable<Planner> {
        PlannerScreen(
            modifier = modifier,
            onSettingButtonClick = { /* 설정 화면으로 이동 */ },
            navigateToSetGoalTime = { navController.navigateToSetGoalTime() },
            navigateToEditGoalTime = { navController.navigateToSetGoalTime() },
            navigateToPlannerCalendar = { navController.navigateToPlannerCalendar() },
            navigateToPlannerDetail = { navController.navigateToPlannerDetial() }
        )
    }

    composable<SetGoalTime> {
        SetGoalTimeScreen(
            //goalTime 인자로 넘기기
            onCloseButtonClicked = { navController.popBackStack() },
            onSetButtonClicked = { navController.popBackStack() },
            modifier = modifier,
        )
    }

    composable<PlannerCalendar> {
        PlannerCalendarScreen(
            modifier = modifier,
            onCloseButtonClicked = { navController.popBackStack() },
            navigateToPlannerDetail = { navController.navigateToPlannerDetial() }
        )
    }

    composable<PlannerDetail> {
        PlannerDetailRoute(
            selectedDay = LocalDate.now(), //추후 선택된 값으로 변경
            onCloseButtonClicked = { navController.popBackStack() },
            modifier = modifier
        )
    }
}

@Serializable
data object Planner : MainTabRoute

@Serializable
data object SetGoalTime : Route

@Serializable
data object PlannerCalendar : Route

@Serializable
data object PlannerDetail : Route