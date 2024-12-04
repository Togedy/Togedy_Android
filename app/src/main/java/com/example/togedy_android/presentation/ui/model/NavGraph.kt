package com.example.togedy_android.presentation.ui.model

sealed class BottomNavRoutes(val route: String) {
    object Planner : BottomNavRoutes("planner")
    object Community : BottomNavRoutes("community")
    object GPT : BottomNavRoutes("gpt")
    object Calendar : BottomNavRoutes("calendar")
    object MyPage : BottomNavRoutes("myPage")
}

sealed class Routes(val route: String) {
    data object LaunchPage : Routes("launchPage")
    data object AddPersonalSchedule : Routes("AddPersonalSchedule")
}

//@Composable
//fun NavGraph(navController: NavHostController) {
//
//    NavHost(navController = navController, startDestination = BottomNavRoutes.GPT.route) {
//
//        composable(BottomNavRoutes.Planner.route) {
//            PlannerScreen(navController)
//        }
//        composable(BottomNavRoutes.Community.route) {
//            CommunityScreen(
//                onMenuBtnClicked = { }
//            )
//        }
//        composable(BottomNavRoutes.GPT.route) {
//            GPTScreen(navController)
//        }
//        composable(BottomNavRoutes.Calendar.route) {
//            CalendarScreen(
//                onCollegeScheduleBtnClicked = { },
//                onPersonalScheduleAddBtnClicked = {
//                    navController.navigate(Routes.AddPersonalSchedule.route)
//                }
//            )
//        }
//        composable(BottomNavRoutes.MyPage.route) {
//            MyPageScreen(navController)
//        }
//
//        composable(Routes.AddPersonalSchedule.route) {
//            AddPersonalSchedule(
//                closeButtonClicked = {
//                    navController.popBackStack()
//                },
//                addButtonClicked = {}
//            )
//        }
//
//    }
//}