package com.example.togedy_android.ui.bottom_nav

import com.example.togedy_android.R
import com.example.togedy_android.ui.Screens

data class BottomNavigationItem(
    var label: String = "",
    var icon: Int = 0,
    var route: String = ""
) {
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
//                label = "스터디",
                icon = R.drawable.ic_bottom_nav_study,
                route = Screens.Home.route
            ),
            BottomNavigationItem(
//                label = "커뮤니티",
                icon = R.drawable.ic_bottom_nav_community,
                route = Screens.Planner.route
            ),
            BottomNavigationItem(
//                label = "GPT",
                icon = R.drawable.ic_home,
                route = Screens.Calendar.route
            ),
            BottomNavigationItem(
//                label = "캘린더",
                icon = R.drawable.ic_bottom_nav_calendar,
                route = Screens.Community.route
            ),
            BottomNavigationItem(
//                label = "마이페이지",
                icon = R.drawable.ic_bottom_nav_user,
                route = Screens.MyPage.route
            ),
        )
    }
}