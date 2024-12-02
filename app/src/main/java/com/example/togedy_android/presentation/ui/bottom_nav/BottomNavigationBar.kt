package com.example.togedy_android.presentation.ui.bottom_nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.togedy_android.presentation.ui.model.Routes
import com.example.togedy_android.presentation.calendar.CalendarScreen
import com.example.togedy_android.presentation.community.CommunityScreen
import com.example.togedy_android.presentation.gptscreen.GPTScreen
import com.example.togedy_android.presentation.mypage.MyPageScreen
import com.example.togedy_android.presentation.planner.PlannerScreen

@Composable
fun BottomNavigationBar() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                BottomNavigationItem().bottomNavigationItems().forEachIndexed { _, navigationItem ->
                    NavigationBarItem(
                        selected = navigationItem.route == currentDestination?.route,
                        label = {
                            Text(navigationItem.label)
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = navigationItem.icon),
                                contentDescription = navigationItem.label
                            )
                        },
                        onClick = {
                            navController.navigate(navigationItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.Home.route,
            modifier = Modifier.padding(paddingValues = paddingValues)) {
            composable(Screens.Home.route) {
                GPTScreen(
                )
            }
            composable(Screens.Planner.route) {
                PlannerScreen(
                )
            }
            composable(Screens.Calendar.route) {
                CalendarScreen(
                    onCollegeScheduleBtnClicked = { },
                    onPersonalScheduleAddBtnClicked = {
                        navController.navigate(Routes.AddPersonalSchedule.route)
                    }
                )
            }
            composable(Screens.Community.route) {
                CommunityScreen(
                    onMenuBtnClicked = {

                    }
                )
            }
            composable(Screens.MyPage.route) {
                MyPageScreen(
                )
            }
        }
    }
}