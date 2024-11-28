package com.example.togedy_android.presentation.ui.bottom_nav

sealed class Screens(val route : String) {
    object Home : Screens("home_screen")
    object Planner : Screens("planner_screen")
    object Calendar : Screens("calendar_screen")
    object Community : Screens("community_screen")
    object MyPage : Screens("my_page_screen")

}