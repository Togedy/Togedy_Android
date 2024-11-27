package com.example.togedy_android.presentation.ui.bottom_nav

import com.example.togedy_android.R
import com.example.togedy_android.presentation.ui.model.BottomNavRoutes

data class BottomNavigationItem(
    var label: String = "",
    var icon: Int = 0,
    var route: String = ""
) {
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "플래너",
                icon = R.drawable.ic_bottom_nav_study,
                route = BottomNavRoutes.Planner.route
            ),
            BottomNavigationItem(
                label = "커뮤니티",
                icon = R.drawable.ic_bottom_nav_community,
                route = BottomNavRoutes.Community.route
            ),
            BottomNavigationItem(
                label = "GPT",
                icon = R.drawable.ic_home,
                route = BottomNavRoutes.GPT.route
            ),
            BottomNavigationItem(
                label = "캘린더",
                icon = R.drawable.ic_bottom_nav_calendar,
                route = BottomNavRoutes.Calendar.route
            ),
            BottomNavigationItem(
                label = "마이페이지",
                icon = R.drawable.ic_bottom_nav_user,
                route = BottomNavRoutes.MyPage.route
            ),
        )
    }
}