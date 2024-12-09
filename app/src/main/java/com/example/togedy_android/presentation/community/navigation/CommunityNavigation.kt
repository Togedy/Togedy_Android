package com.example.togedy_android.presentation.community.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.togedy_android.core.navigation.MainTabRoute
import com.example.togedy_android.core.navigation.Route
import com.example.togedy_android.presentation.community.CommunityBoardScreen
import com.example.togedy_android.presentation.community.CommunityDetailScreen
import com.example.togedy_android.presentation.community.CommunityScreen
import kotlinx.serialization.Serializable

fun NavHostController.navigateToCommunity(navOptions: NavOptions? = null) = navigate(Community)

fun NavHostController.navigateToCommunityBoard(navOptions: NavOptions? = null) = navigate(CommunityBoard)

fun NavGraphBuilder.communityScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    composable<Community> {
        CommunityScreen (
            navigateToCommunityBoard = { navController.navigateToCommunityBoard() }
        )
    }

    composable<CommunityBoard> {
        CommunityBoardScreen (
        )
    }
}

@Serializable
data object Community: MainTabRoute
@Serializable
data object CommunityBoard: Route