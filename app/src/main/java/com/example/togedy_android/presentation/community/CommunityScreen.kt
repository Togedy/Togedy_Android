package com.example.togedy_android.presentation.community

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.domain.type.WritingType
import com.example.togedy_android.presentation.community.component.CommunityFastTab
import com.example.togedy_android.presentation.community.component.CommunityHomeTopBar
import com.example.togedy_android.presentation.community.component.HomeBulletinBoard
import com.example.togedy_android.presentation.community.component.TrendingPostsSection
import com.example.togedy_android.util.noRippleClickable
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CommunityScreen(
    viewModel: CommunityViewModel = hiltViewModel(),
    navigateToCommunityBoard: (String, String?) -> Unit,
    navigateToCommunityAdd: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    Scaffold {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(color = TogedyTheme.colors.white)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                CommunityHomeTopBar(
                    onMenuBtnClicked = {
                        coroutineScope.launch {
                            if (drawerState.isClosed) {
                                drawerState.open()
                            }
                        }
                    }
                )

                CommunityFastTab(
                    navigateToCommunityBoard = navigateToCommunityBoard
                )

                Spacer(modifier = Modifier.height(30.dp))
                TrendingPostsSection()

                Spacer(modifier = Modifier.height(30.dp))
                HomeBulletinBoard(
                    onBulletinBoardMoreClicked = navigateToCommunityBoard
                )
            }

            if (drawerState.isOpen) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = TogedyTheme.colors.black.copy(alpha = 0.5f))
                        .noRippleClickable {
                            coroutineScope.launch {
                                drawerState.close()
                            }
                        }
                )

                CommunitySideBar(
                    modifier = Modifier.align(Alignment.BottomEnd),
                    navigateToCommunityBoard = navigateToCommunityBoard
                )
            }
        }
    }
}

@Composable
fun CommunitySideBar(
    modifier: Modifier,
    navigateToCommunityBoard: (String, String?) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .width(300.dp)
            .background(color = TogedyTheme.colors.white),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = TogedyTheme.colors.gray100)
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.img_example_board),
                        contentDescription = "사용자 이미지",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(shape = RoundedCornerShape(100.dp))
                    )

                    Text(
                        text = "태정",
                        modifier = Modifier.padding(start = 10.dp),
                        color = TogedyTheme.colors.black,
                        style = TogedyTheme.typography.body2M
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(color = TogedyTheme.colors.yellow500)
                        .padding(horizontal = 14.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_community_writing),
                        contentDescription = "글 작성 이미지",
                        modifier = Modifier
                            .size(24.dp)
                    )

                    Text(
                        text = "글 작성하기",
                        modifier = Modifier.padding(start = 8.dp),
                        color = TogedyTheme.colors.white,
                        style = TogedyTheme.typography.body2B
                    )
                }
            }

            item { SideBoardTitle(stringResource(R.string.side_title_community),
                ) }

            itemsIndexed(WritingType.entries.slice(0..2)) { index: Int, item: WritingType ->
                SideBoardInfo(
                    boardName = item.type,
                    navigateToCommunityBoard = navigateToCommunityBoard
                )
            }

            item { HorizontalDivider(modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = TogedyTheme.colors.gray200) }

            item { SideBoardTitle(stringResource(R.string.side_title_univ)) }

            itemsIndexed(WritingType.entries.slice(3 until WritingType.entries.size)) { index: Int, item: WritingType ->
                SideBoardInfo(
                    boardName = item.type,
                    navigateToCommunityBoard = navigateToCommunityBoard
                )
            }
        }
    }
}

@Composable
fun SideBoardTitle(
    boardTitle: String,
) {
    Text(
        text = boardTitle,
        modifier = Modifier.fillMaxWidth(),
        color = TogedyTheme.colors.gray400,
        style = TogedyTheme.typography.body3B
    )
}

@Composable
fun SideBoardInfo(
    boardName: String,
    viewModel: CommunityViewModel = hiltViewModel(),
    navigateToCommunityBoard: (String, String?) -> Unit
) {
    Text(
        text = boardName,
        modifier = Modifier.fillMaxWidth()
            .clickable {
                val writingType = viewModel.getBoardType(boardName)
                if (writingType != null) {
                    val boardType = viewModel.getBoardPath(writingType)
                    val univName = if (boardType == "univ") writingType.type else null
                    Log.d("Navigation", "boardType: $boardType, univName: $univName")
                    navigateToCommunityBoard(boardType, univName)
                } else {
                    Log.e("pathError", "없는 이름, $boardName")
                }
            },
        color = TogedyTheme.colors.black,
        style = TogedyTheme.typography.body2M
    )
}
