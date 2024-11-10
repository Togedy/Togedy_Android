package com.example.togedy_android.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.R
import com.example.togedy_android.ui.component.community.CommunityFastTab
import com.example.togedy_android.ui.component.community.CommunityHomeTopBar
import com.example.togedy_android.ui.theme.TogedyTheme
import com.example.togedy_android.ui.theme.Togedy_AndroidTheme

@Composable
fun CommunityScreen() {
    Togedy_AndroidTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = colorScheme.background
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


            }
        }
    }
}

@Preview
@Composable
fun CommunityScreenPreview(modifier: Modifier = Modifier) {
    CommunityScreen()
}