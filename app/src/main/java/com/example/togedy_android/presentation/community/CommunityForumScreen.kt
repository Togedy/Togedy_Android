package com.example.togedy_android.presentation.community

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.component.TopBarBasic
import com.example.togedy_android.core.design_system.theme.Togedy_AndroidTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CommunityForumScreen(
) {
    Togedy_AndroidTheme {
        Scaffold(
            floatingActionButton = { },
            floatingActionButtonPosition = FabPosition.End
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
            ) {
                TopBarBasic(
                    leftButtonIcon = R.drawable.ic_chevron_left,
                    title = stringResource(R.string.community_general_forum)
                ) {

                }
                LazyColumn() {

                }
            }
        }

    }
}

@Preview
@Composable
fun CommunityForumScreenPreview(){
    CommunityForumScreen()
}