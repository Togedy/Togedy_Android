package com.example.togedy_android.presentation.planner.planner.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.togedy_android.core.navigation.MainTabRoute
import com.example.togedy_android.core.navigation.Route
import com.example.togedy_android.presentation.planner.planner.PlannerRoute
import com.example.togedy_android.presentation.planner.plannerCalendar.PlannerCalendarRoute
import com.example.togedy_android.presentation.planner.plannerDetail.PlannerDetailRoute
import com.example.togedy_android.presentation.planner.setGoalTime.SetGoalTimeScreen
import com.example.togedy_android.presentation.planner.stopwatch.StopWatchScreen
import kotlinx.serialization.Serializable
import java.time.LocalDate

fun NavHostController.navigateToPlanner(navOptions: NavOptions? = null) =
    navigate(Planner)

fun NavHostController.navigateToSetGoalTime(navOptions: NavOptions? = null) =
    navigate(SetGoalTime)

fun NavHostController.navigateToPlannerCalendar(navOptions: NavOptions? = null) =
    navigate(PlannerCalendar)

fun NavHostController.navigateToPlannerDetail(navOptions: NavOptions? = null) =
    navigate(PlannerDetail)

fun NavHostController.navigateToStopWatch(navOptions: NavOptions? = null) =
    navigate(StopWatch)


fun NavGraphBuilder.plannerScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    composable<Planner> {
        PlannerRoute(
            modifier = modifier,
            onSettingButtonClick = { navController.navigateToStopWatch() }, //추후 버튼 디자인 변경
            navigateToSetGoalTime = { navController.navigateToSetGoalTime() },
            navigateToEditGoalTime = { navController.navigateToSetGoalTime() },
            navigateToPlannerCalendar = { navController.navigateToPlannerCalendar() },
            navigateToPlannerDetail = { navController.navigateToPlannerDetail() }
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
        PlannerCalendarRoute(
            modifier = modifier,
            onCloseButtonClicked = { navController.popBackStack() },
            navigateToPlannerDetail = { navController.navigateToPlannerDetail() }
        )
    }

    composable<PlannerDetail> {
        PlannerDetailRoute(
            selectedDay = LocalDate.now(), //추후 선택된 값으로 변경
            onCloseButtonClicked = { navController.popBackStack() },
            onRightButtonClicked = { navController.navigateToStopWatch() },
            modifier = modifier
        )
    }

    composable<StopWatch>{
        StopWatchScreen(
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

@Serializable
data object StopWatch : Route