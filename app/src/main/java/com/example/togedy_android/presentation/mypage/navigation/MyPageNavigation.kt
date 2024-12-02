package com.example.togedy_android.presentation.mypage.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.togedy_android.core.navigation.MainTabRoute
import com.example.togedy_android.presentation.mypage.MyPageScreen
import kotlinx.serialization.Serializable

fun NavHostController.navigateToMyPage(navOptions: NavOptions? = null) = navigate(MyPage)

fun NavGraphBuilder.myPageScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    composable<MyPage> {
        MyPageScreen()
    }
}

@Serializable
data object MyPage : MainTabRoute