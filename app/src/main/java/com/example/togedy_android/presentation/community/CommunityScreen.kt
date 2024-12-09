package com.example.togedy_android.presentation.community

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.presentation.community.component.CommunityFastTab
import com.example.togedy_android.presentation.community.component.CommunityHomeTopBar
import com.example.togedy_android.presentation.community.component.HomeBulletinBoard
import com.example.togedy_android.presentation.community.component.TrendingPostsSection
import com.example.togedy_android.core.design_system.theme.Togedy_AndroidTheme

@Composable
fun CommunityScreen(
    navigateToCommunityBoard: () -> Unit
) {
    Togedy_AndroidTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = TogedyTheme.colors.white
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CommunityHomeTopBar(
                    onMenuBtnClicked = { }
                )

                CommunityFastTab(
                    onStudyRecordItemClicked = { },
                    onCollegeItemClicked = { },
                    onMarketplaceClicked = { }
                )

                Spacer(modifier = Modifier.height(30.dp))
                TrendingPostsSection()

                Spacer(modifier = Modifier.height(30.dp))
                HomeBulletinBoard(
                    onBulletinBoardMoreClicked = navigateToCommunityBoard
                )
            }
        }
    }
}

@Preview
@Composable
fun CommunityScreenPreview() {
    CommunityScreen(
        navigateToCommunityBoard = { }
    )
}