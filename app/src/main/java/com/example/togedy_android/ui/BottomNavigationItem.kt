package com.example.togedy_android.ui

import com.example.togedy_android.R

data class BottomNavigationItem(
    var label: String = "",
    var icon: Int = 0,
    var route: String = ""
) {
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "홈",
                icon = R.drawable.ic_home,
                route = Screens.Home.route
            ),
            BottomNavigationItem(
                label = "플래너",
                icon = R.drawable.ic_home,
                route = Screens.Planner.route
            ),
            BottomNavigationItem(
                label = "캘린더",
                icon = R.drawable.ic_home,
                route = Screens.Calendar.route
            ),
            BottomNavigationItem(
                label = "커뮤니티",
                icon = R.drawable.ic_home,
                route = Screens.Community.route
            ),
            BottomNavigationItem(
                label = "마이페이지",
                icon = R.drawable.ic_home,
                route = Screens.MyPage.route
            ),
        )
    }
}