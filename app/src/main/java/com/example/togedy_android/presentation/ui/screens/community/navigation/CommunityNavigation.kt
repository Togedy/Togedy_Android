package com.example.togedy_android.presentation.ui.screens.community.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.togedy_android.core.navigation.MainTabRoute

fun NavHostController.navigateToCommunity(navOptions: NavOptions? = null) = navigate(Community)

fun NavGraphBuilder.communityScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    composable<Community> {

    }
}

data object Community: MainTabRoute