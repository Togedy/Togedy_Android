package com.example.togedy_android.ui.model

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.togedy_android.ui.screens.CalendarScreen
import com.example.togedy_android.ui.screens.CommunityScreen
import com.example.togedy_android.ui.screens.GPTScreen
import com.example.togedy_android.ui.screens.MyPageScreen
import com.example.togedy_android.ui.screens.PlannerScreen

sealed class BottomNavRoutes(val route : String) {
    object Planner : BottomNavRoutes("planner")
    object Community : BottomNavRoutes("community")
    object GPT : BottomNavRoutes("gpt")
    object Calendar : BottomNavRoutes("calendar")
    object MyPage : BottomNavRoutes("myPage")
}

sealed class Routes(val route: String) {
    data object LaunchPage : Routes("launchPage")
    
}

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = BottomNavRoutes.GPT.route) {

        composable(BottomNavRoutes.Planner.route) {
            PlannerScreen(navController)
        }
        composable(BottomNavRoutes.Community.route) {
            CommunityScreen(navController)
        }
        composable(BottomNavRoutes.GPT.route) {
            GPTScreen(navController)
        }
        composable(BottomNavRoutes.Calendar.route) {
            CalendarScreen(navController)
        }
        composable(BottomNavRoutes.MyPage.route) {
            MyPageScreen(navController)
        }
        
    }
}