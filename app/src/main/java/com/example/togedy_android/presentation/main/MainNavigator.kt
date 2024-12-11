package com.example.togedy_android.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.togedy_android.core.navigation.Route
import com.example.togedy_android.presentation.calendar.navigation.navigateToCalendar
import com.example.togedy_android.presentation.community.navigation.navigateToCommunity
import com.example.togedy_android.presentation.gptscreen.navigation.Gpt
import com.example.togedy_android.presentation.gptscreen.navigation.navigateToGPT
import com.example.togedy_android.presentation.mypage.navigation.navigateToMyPage
import com.example.togedy_android.presentation.planner.planner.navigation.navigateToPlanner

class MainNavigator(
    val navController: NavHostController,
) {
    val startDestination: Route = Gpt

    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination


    val currentTab: MainTab?
        @Composable get() = MainTab.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

    @Composable
    fun showBottomBar() = MainTab.contains { tab ->
        currentDestination?.hasRoute(tab::class) == true
    }

    fun navigate(tab: MainTab) {
        val mainNavOption = navOptions {
            popUpTo(navController.graph.startDestinationId) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTab.PLANNER -> navController.navigateToPlanner(navOptions = mainNavOption)
            MainTab.COMMUNITY -> navController.navigateToCommunity(navOptions = mainNavOption)
            MainTab.GPT -> navController.navigateToGPT(navOptions = mainNavOption)
            MainTab.CALENDAR -> navController.navigateToCalendar(navOptions = mainNavOption)
            MainTab.MYPAGE -> navController.navigateToMyPage(navOptions = mainNavOption)
        }
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController()
):MainNavigator = remember(navController) {
    MainNavigator(navController)
}