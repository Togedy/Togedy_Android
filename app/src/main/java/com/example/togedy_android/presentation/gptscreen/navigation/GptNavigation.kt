package com.example.togedy_android.presentation.gptscreen.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.togedy_android.core.navigation.MainTabRoute
import com.example.togedy_android.presentation.gptscreen.GPTScreen
import kotlinx.serialization.Serializable

fun NavHostController.navigateToGPT(navOptions: NavOptions? = null) = navigate(Gpt)

fun NavGraphBuilder.gptScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    composable<Gpt> {
        GPTScreen(
            modifier = modifier,
        )
    }
}

@Serializable
data object Gpt: MainTabRoute