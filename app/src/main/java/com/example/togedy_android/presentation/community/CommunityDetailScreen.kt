package com.example.togedy_android.presentation.community

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.component.TopBarBasic
import com.example.togedy_android.core.design_system.theme.TogedyTheme

@Composable
fun CommunityDetailScreen(
    postId: Int,
    modifier: Modifier = Modifier,
    viewModel: CommunityViewModel = hiltViewModel()
) {
    var heartClicked = true

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = TogedyTheme.colors.white)
            .padding(horizontal = 18.dp, vertical = 16.dp)
    ) {
        item {
            TopBarBasic(
                leftButtonIcon = R.drawable.ic_chevron_left,
                title = "",
                onLeftButtonClicked = { }
            )
        }

        item {
            DetailDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, bottom = 7.dp)
            )
        }


        item {
            Row(
                modifier = Modifier.padding(bottom = 14.dp)
            ) {
                Card(
                    modifier = Modifier.padding(vertical = 3.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.img_example_board),
                        contentDescription = "사용자 프로필 사진"
                    )
                }
                Column(
                    modifier = Modifier.padding(start = 7.dp)
                ) {
                    Text(
                        text = "사용자 이름",
                        color = TogedyTheme.colors.gray700,
                        style = TogedyTheme.typography.body2B
                    )
                    Text(
                        text = "2024.06.06 13:00",
                        color = TogedyTheme.colors.gray400,
                        style = TogedyTheme.typography.caption
                    )
                }
            }
        }

        item {
            CommunityDetailImages()
        }

        item {
            Text(
                text = "건대 근처 맛집 팝니다",
                modifier = Modifier.padding(bottom = 16.dp),
                color = TogedyTheme.colors.black,
                style = TogedyTheme.typography.headline3B
            )
        }

        item {
            Text(
                text = "건대 근처 학원 다니면서 맛집 정말 많이 다녀봤고, 제가 맛잘알로써 진짜 맛있는 집들만 정리해뒀습니다. \n" +
                        "\n" +
                        "가격은 3000원!!\n" +
                        "편하게 연락주세요",
                color = TogedyTheme.colors.gray800,
                style = TogedyTheme.typography.body1M,
                modifier = Modifier.padding(bottom = 38.dp)
            )
        }

        item {
            Row(
                modifier = Modifier.padding(bottom = 14.dp)
            ) {
                Image(
                    painter = painterResource(
                        if (heartClicked) R.drawable.ic_heart else R.drawable.ic_heart_clicked
                    ),
                    contentDescription = "좋아요 버튼",
                    modifier = Modifier.padding(end = 6.dp)
                )
                Text(
                    text = "3",
                    modifier = Modifier.padding(end = 10.dp),
                    color = if (heartClicked) TogedyTheme.colors.black else TogedyTheme.colors.gray600,
                    style = TogedyTheme.typography.body1M
                )
                Image(
                    painter = painterResource(R.drawable.ic_message),
                    contentDescription = "댓글 버튼",
                    modifier = Modifier.padding(end = 6.dp)
                )
                Text(
                    text = "2",
                    color = TogedyTheme.colors.gray600,
                    style = TogedyTheme.typography.body1M
                )
            }
        }

        item {
            DetailDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )
        }

        item {
            Text(
                text = stringResource(R.string.community_detail_comment),
                color = TogedyTheme.colors.gray300,
                style = TogedyTheme.typography.body3B
            )
        }

        item {
            CommentSection()
        }
    }
}

@Composable
fun CommentSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        repeat(5) { index ->
            CommentItem(
                userImage = R.drawable.img_example_board,
                username = "익명",
                content = "저도 오늘 갔는데 생각보다 많이 바뀌었더라구요..",
                replies = listOf("오 인정..", "ㄹㅇㅋㅋ")
            )
        }
    }
}

@Composable
fun CommentItem(userImage: Int, username: String, content: String, replies: List<String>) {
    Column(
        modifier = Modifier.padding(vertical = 5.dp),
    ) {
        DetailComment(
            userImage = userImage,
            username = username,
            content = content)

        Column() {
            replies.forEach { reply ->
                Row(
                    modifier = Modifier.padding(top = 9.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_community_reply),
                        contentDescription = "답글 아이콘"
                    )
                    Column() {
                        DetailComment(
                            userImage = userImage,
                            username = username,
                            content = content
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun DetailComment(
    userImage: Int, username: String, content: String
){
    Row(
        modifier = Modifier.padding(bottom = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(userImage),
            contentDescription = "유저 이미지",
            modifier = Modifier
                .size(20.dp)
                .padding(vertical = 2.dp)
                .padding(end = 6.dp)
        )
        Text(
            text = username,
            color = TogedyTheme.colors.gray700,
            style = TogedyTheme.typography.body3B
        )

    }

    Text(
        text = content,
        modifier = Modifier.padding(bottom = 10.dp),
        color = TogedyTheme.colors.gray700,
        style = TogedyTheme.typography.body2M
    )

    Row(
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_heart_clicked),
            contentDescription = "좋아요 버튼",
            modifier = Modifier.size(16.dp).padding(end = 6.dp)
        )
        Text(
            text = "3",
            color = TogedyTheme.colors.gray700,
            style = TogedyTheme.typography.body3M
        )
    }

    DetailDivider(modifier = Modifier.fillMaxWidth())
}

@Composable
fun DetailDivider(
    modifier: Modifier
) {
    HorizontalDivider(
        modifier = modifier,
        thickness = 1.dp,
        color = TogedyTheme.colors.gray300
    )
}

@Composable
fun CommunityDetailImages() {
    val list = arrayListOf<Int>()
    for (i in 0..10) {
        list.add(R.drawable.img_example_board)
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(list.size) { index ->
            CommunityDetailImage(list[index])
        }
    }
}

@Composable
fun CommunityDetailImage(image: Int) {
    Image(
        painter = painterResource(image),
        contentDescription = "게시글 사진",
        modifier = Modifier
            .width(160.dp)
            .aspectRatio(1f)
    )
}

@Preview
@Composable
fun CommunityDetailPreviewScreen() {
    CommunityDetailScreen(
        postId = 1,
        viewModel = viewModel()
    )
}