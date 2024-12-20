package com.example.togedy_android.presentation.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.togedy_android.core.navigation.Route
import com.example.togedy_android.presentation.calendar.calendar.navigation.calendarScreen
import com.example.togedy_android.presentation.main.component.MainBottomBars
import com.example.togedy_android.presentation.community.navigation.communityScreen
import com.example.togedy_android.presentation.gptscreen.navigation.gptScreen
import com.example.togedy_android.presentation.mypage.navigation.myPageScreen
import com.example.togedy_android.presentation.planner.planner.navigation.plannerScreen

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainNavigator: MainNavigator = rememberMainNavigator()
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            MainBottomBars(
                tabs = MainTab.entries,
                visibility = mainNavigator.showBottomBar(),
                onTabSelect = mainNavigator::navigate,
                selectedTab = mainNavigator.currentTab
            )
        }
    ) { innerPadding ->
        MainNavHost(
            modifier = Modifier.padding(innerPadding),
            navController = mainNavigator.navController,
            startDestination = mainNavigator.startDestination
        )
    }
}

@Composable
private fun MainNavHost(
    navController: NavHostController,
    startDestination: Route,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        plannerScreen(
            navController = navController,
            modifier = modifier
        )
        communityScreen(
            navController = navController,
            modifier = modifier
        )
        gptScreen(
            navController = navController,
            modifier = modifier
        )
        calendarScreen(
            navController = navController,
            modifier = modifier
        )
        myPageScreen(
            navController = navController,
            modifier = modifier
        )
    }
}