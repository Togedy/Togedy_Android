package com.example.togedy_android.presentation.planner.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.togedy_android.core.navigation.MainTabRoute
import com.example.togedy_android.core.navigation.Route
import com.example.togedy_android.presentation.planner.PlannerScreen
import com.example.togedy_android.presentation.planner.setGoalTime.SetGoalTimeScreen
import kotlinx.serialization.Serializable

fun NavHostController.navigateToPlanner(navOptions: NavOptions? = null) = navigate(Planner)

fun NavHostController.navigateToSetGoalTime(navOptions: NavOptions?= null) = navigate(SetGoalTime)

fun NavGraphBuilder.plannerScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    composable<Planner> {
        PlannerScreen(
            modifier = modifier,
            onSettingButtonClick = { /* 설정 화면으로 이동 */ },
            navigateToSetGoalTime = { navController.navigateToSetGoalTime() },
            navigateToEditGoalTime = { navController.navigateToSetGoalTime() }
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
}

@Serializable
data object Planner: MainTabRoute
@Serializable
data object SetGoalTime: Route