package com.example.togedy_android.presentation.ui.screens.community

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.presentation.ui.screens.community.component.CommunityFastTab
import com.example.togedy_android.presentation.ui.screens.community.component.CommunityHomeTopBar
import com.example.togedy_android.presentation.ui.screens.community.component.HomeBulletinBoard
import com.example.togedy_android.presentation.ui.screens.community.component.TrendingPostsSection
import com.example.togedy_android.ui.theme.TogedyTheme
import com.example.togedy_android.ui.theme.Togedy_AndroidTheme

@Composable
fun CommunityScreen(
    onMenuBtnClicked: () -> Unit,
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
                    onMenuBtnClicked = onMenuBtnClicked
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
                    onBulletinBoardMoreClicked = { }
                )
            }
        }
    }
}

@Preview
@Composable
fun CommunityScreenPreview() {
    CommunityScreen(
        onMenuBtnClicked = { }
    )
}