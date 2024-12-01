package com.example.togedy_android.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.togedy_android.core.navigation.Route
import com.example.togedy_android.presentation.main.MainTab.Companion.Gpt
import com.example.togedy_android.presentation.ui.screens.community.navigation.navigateToCommunity
import com.example.togedy_android.presentation.ui.screens.gptscreen.navigation.navigateToGPT

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
            MainTab.PLANNER -> {}
            MainTab.COMMUNITY -> navController.navigateToCommunity()
            MainTab.GPT -> navController.navigateToGPT()
            MainTab.CALENDAR -> {}
            MainTab.MYPAGE -> {}
        }
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController()
):MainNavigator = remember(navController) {
    MainNavigator(navController)
}