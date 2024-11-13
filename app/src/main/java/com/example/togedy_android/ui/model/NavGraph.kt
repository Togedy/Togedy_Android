package com.example.togedy_android.ui.model

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.togedy_android.ui.screens.CalendarScreen
import com.example.togedy_android.ui.screens.community.CommunityScreen
import com.example.togedy_android.ui.screens.GPTScreen
import com.example.togedy_android.ui.screens.MyPageScreen
import com.example.togedy_android.ui.screens.PlannerScreen
import com.example.togedy_android.ui.screens.calendar.AddPersonalSchedule
import com.example.togedy_android.ui.screens.community.AddWriting

sealed class BottomNavRoutes(val route: String) {
    object Planner : BottomNavRoutes("planner")
    object Community : BottomNavRoutes("community")
    object GPT : BottomNavRoutes("gpt")
    object Calendar : BottomNavRoutes("calendar")
    object MyPage : BottomNavRoutes("myPage")
}

sealed class Routes(val route: String) {
    data object LaunchPage : Routes("launchPage")
    //캘린더
    data object AddPersonalSchedule : Routes("AddPersonalSchedule")

    //커뮤니티
    data object AddWriting: Routes("AddWriting")
}

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = BottomNavRoutes.GPT.route) {

        composable(BottomNavRoutes.Planner.route) {
            PlannerScreen(navController)
        }
        composable(BottomNavRoutes.Community.route) {
            CommunityScreen(
                onMenuBtnClicked = {
                    navController.navigate(Routes.AddWriting.route)
                }
            )
        }
        composable(BottomNavRoutes.GPT.route) {
            GPTScreen(navController)
        }
        composable(BottomNavRoutes.Calendar.route) {
            CalendarScreen(
                onCollegeScheduleBtnClicked = { },
                onPersonalScheduleAddBtnClicked = {
                    navController.navigate(Routes.AddPersonalSchedule.route)
                }
            )
        }
        composable(BottomNavRoutes.MyPage.route) {
            MyPageScreen(navController)
        }

        //캘린더
        composable(Routes.AddPersonalSchedule.route) {
            AddPersonalSchedule(
                closeButtonClicked = {
                    navController.popBackStack()
                },
                addButtonClicked = {}
            )
        }


        //커뮤니티
        composable(Routes.AddWriting.route) {
            AddWriting(
                closeButtonClicked = {
                    navController.popBackStack()
                }
            )
        }

    }
}