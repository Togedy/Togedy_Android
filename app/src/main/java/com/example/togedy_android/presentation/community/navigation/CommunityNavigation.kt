package com.example.togedy_android.presentation.community.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.togedy_android.core.navigation.MainTabRoute
import com.example.togedy_android.core.navigation.Route
import com.example.togedy_android.presentation.community.AddWriting
import com.example.togedy_android.presentation.community.CommunityBoardScreen
import com.example.togedy_android.presentation.community.CommunityDetailScreen
import com.example.togedy_android.presentation.community.CommunityScreen
import kotlinx.serialization.Serializable

fun NavHostController.navigateToCommunity(navOptions: NavOptions? = null) = navigate(Community)

fun NavHostController.navigateToCommunityBoard(navOptions: NavOptions? = null) = navigate(CommunityBoard)

fun NavHostController.navigateToCommunityAdd(navOptions: NavOptions? = null) = navigate(CommunityAdd)

fun NavController.navigateToCommunityDetail(postId: Int = 1, navOptions: NavOptions? = null) =
    navigate(
        route = CommunityDetail(postId),
        navOptions = navOptions,
    )

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
            modifier = modifier,
            navigateToCommunityDetail = { navController.navigateToCommunityDetail( postId = it) },
            navigateToCommunityAdd = { navController.navigateToCommunityAdd() }
        )
    }

    composable<CommunityAdd> {
        AddWriting (
            closeButtonClicked = { }
        )
    }

    composable<CommunityDetail>{
        val args = it.toRoute<CommunityDetail>()
        CommunityDetailScreen(
            postId = args.postId,
            modifier = modifier
        )
    }
}

@Serializable
data object Community: MainTabRoute
@Serializable
data object CommunityBoard: Route
@Serializable
data class CommunityDetail(val postId: Int) : Route
@Serializable
data object CommunityAdd: Route