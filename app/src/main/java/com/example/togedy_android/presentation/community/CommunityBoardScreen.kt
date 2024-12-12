package com.example.togedy_android.presentation.community

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.component.TopBarBasic
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.core.design_system.theme.Togedy_AndroidTheme
import com.example.togedy_android.domain.model.BoardList
import com.example.togedy_android.presentation.community.component.CommunityForumListItem
import com.example.togedy_android.presentation.community.state.BoardListState
import com.example.togedy_android.util.formatToSimpleDate

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CommunityBoardScreen(
    modifier: Modifier = Modifier,
    viewModel: CommunityViewModel = hiltViewModel(),
    boardType: String,
    univName: String?,
    navigateToCommunityDetail: (Int) -> Unit,
    navigateToCommunityAdd: () -> Unit
) {
    val boardListState by viewModel.boardListState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getBoardList(boardType = boardType, univName = univName)
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(color = TogedyTheme.colors.white),
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(color = TogedyTheme.colors.yellow500)
                    .padding(14.dp)
                    .clickable { navigateToCommunityAdd() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_community_writing),
                    contentDescription = "일정 추가 버튼",
                    tint = TogedyTheme.colors.white,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = TogedyTheme.colors.white)
                .padding(horizontal = 18.dp)
                .padding(top = 15.dp)
        ) {
            TopBarBasic(
                leftButtonIcon = R.drawable.ic_chevron_left,
                title = if (univName == null) viewModel.getBoardTitlePath(boardType = boardType)!!.type else univName,
                onLeftButtonClicked = {},
            )

            when (boardListState) {
                is BoardListState.Loading -> {}
                is BoardListState.Success -> {
                    BoardListInfo(
                        boardListData = (boardListState as BoardListState.Success).data,
                        boardType = boardType,
                        navigateToCommunityDetail = navigateToCommunityDetail
                    )
                }

                is BoardListState.Failure -> {}
                else -> {}
            }
        }
    }

}

@Composable
fun BoardListInfo(
    boardListData: List<BoardList>,
    boardType: String,
    navigateToCommunityDetail: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = TogedyTheme.colors.white)
            .padding(top = 15.dp)
    ) {
        itemsIndexed(items = boardListData) { index, item ->
            CommunityForumListItem(
                postId = item.postId,
                title = item.title,
                date = item.createdAt.formatToSimpleDate(),
                content = item.content,
                type = boardType,
                image = item.postImages?.firstOrNull(),
                navigateToCommunityDetail = navigateToCommunityDetail
            )
        }
    }
}


@Preview
@Composable
fun CommunityForumScreenPreview() {
    Togedy_AndroidTheme {
        CommunityBoardScreen(
            boardType = "free",
            univName = null,
            navigateToCommunityDetail = { },
            navigateToCommunityAdd = { }
        )
    }
}