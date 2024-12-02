package com.example.togedy_android.presentation.planner.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.togedy_android.core.navigation.MainTabRoute
import com.example.togedy_android.presentation.planner.PlannerScreen
import kotlinx.serialization.Serializable

fun NavHostController.navigateToPlanner(navOptions: NavOptions? = null) = navigate(Planner)

fun NavGraphBuilder.plannerScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    composable<Planner> {
        PlannerScreen()
    }
}

@Serializable
data object Planner: MainTabRoute