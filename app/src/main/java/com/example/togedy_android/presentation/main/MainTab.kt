package com.example.togedy_android.presentation.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.example.togedy_android.core.navigation.MainTabRoute
import com.example.togedy_android.R

enum class MainTab(
    @StringRes val title: Int,
    @DrawableRes val iconRes: Int,
    val route: MainTabRoute,
) {
    PLANNER(
        title = R.string.main_bottom_bar_planner,
        iconRes = R.drawable.ic_bottom_nav_study,
        route = Planner
    ),
    COMMUNITY(
        title = R.string.main_bottom_bar_community,
        iconRes = R.drawable.ic_bottom_nav_community,
        route = Community
    ),
    GPT(
        title = R.string.main_bottom_bar_gpt,
        iconRes = R.drawable.ic_home,
        route = Gpt
    ),
    CALENDAR(
        title = R.string.main_bottom_bar_calendar,
        iconRes = R.drawable.ic_bottom_nav_calendar,
        route = Calendar
    ),
    MYPAGE(
        title = R.string.main_bottom_bar_my,
        iconRes = R.drawable.ic_bottom_nav_user,
        route = MyPage
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? =
            entries.find { predicate(it.route) }

        @Composable
        fun contains(predicate: @Composable (MainTabRoute) -> Boolean): Boolean =
            entries.map{it.route}.any { predicate(it) }

        data object Planner: MainTabRoute
        data object Community: MainTabRoute
        data object Gpt: MainTabRoute
        data object Calendar: MainTabRoute
        data object MyPage: MainTabRoute
    }
}