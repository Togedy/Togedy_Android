package com.example.togedy_android.presentation.community

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.component.TopBarBasic
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.core.design_system.theme.Togedy_AndroidTheme
import com.example.togedy_android.domain.model.BoardList
import com.example.togedy_android.presentation.community.component.CommunityForumListItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CommunityForumScreen(
    viewModel: CommunityViewModel = hiltViewModel(),
    boardType: String = "free"
) {
    val boardListData by viewModel.boardListData.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getBoardList(boardType = boardType)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                modifier = Modifier.background(
                    color = TogedyTheme.colors.yellow500
                ),
                contentColor = TogedyTheme.colors.white,
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_community_writing),
                    contentDescription = "글 작성 버튼"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopBarBasic(
                leftButtonIcon = R.drawable.ic_chevron_left,
                title = stringResource(R.string.community_general_forum),
                onLeftButtonClicked = {}
            )

            BoardListInfo(
                boardListData = boardListData,
                boardType = boardType
            )
        }
    }

}

@Composable
fun BoardListInfo(
    boardListData: ArrayList<BoardList>,
    boardType: String
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        itemsIndexed(items = boardListData) { index, item ->
            CommunityForumListItem(
                title = item.title,
                date = item.createdAt,
                content = item.content,
                type = boardType,
                image = item.postImages[0].toInt()
            )
        }
    }
}


@Preview
@Composable
fun CommunityForumScreenPreview() {
    Togedy_AndroidTheme {
        CommunityForumScreen()
    }
}