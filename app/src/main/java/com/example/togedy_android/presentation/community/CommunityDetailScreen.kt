package com.example.togedy_android.presentation.community

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.component.TopBarBasic
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.domain.model.DetailComments
import com.example.togedy_android.domain.model.DetailReplies
import com.example.togedy_android.presentation.community.state.BoardDetailState
import com.example.togedy_android.presentation.community.state.BoardListState
import com.example.togedy_android.util.formatToSimpleDate

@Composable
fun CommunityDetailScreen(
    postId: Int, modifier: Modifier = Modifier, viewModel: CommunityViewModel = hiltViewModel()
) {
    val boardDetailState by viewModel.boardDetailState.collectAsState()
    val boardDetailData by viewModel.boardDetailData.collectAsState()
    val heartClicked = viewModel.boardDetailData.collectAsState().value.postLike

    LaunchedEffect(Unit) {
        viewModel.getBoardDetail(postId)

    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = TogedyTheme.colors.white)
            .padding(horizontal = 18.dp, vertical = 16.dp)
    ) {
        item {
            TopBarBasic(leftButtonIcon = R.drawable.ic_chevron_left,
                title = "",
                onLeftButtonClicked = { })
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
                        text = "태정",
                        color = TogedyTheme.colors.gray700,
                        style = TogedyTheme.typography.body2B
                    )
                    Text(
                        text = boardDetailData.createdAt.formatToSimpleDate(),
                        color = TogedyTheme.colors.gray400,
                        style = TogedyTheme.typography.caption
                    )
                }
            }
        }

        item {
            CommunityDetailImages(boardDetailData.postImages)
        }

        item {
            Text(
                text = boardDetailData.title,
                modifier = Modifier.padding(bottom = 16.dp),
                color = TogedyTheme.colors.black,
                style = TogedyTheme.typography.headline3B
            )
        }

        item {
            Text(
                text = boardDetailData.content,
                color = TogedyTheme.colors.gray800,
                style = TogedyTheme.typography.body1M,
                modifier = Modifier.padding(bottom = 38.dp)
            )
        }

        item {
            Row(
                modifier = Modifier.padding(bottom = 14.dp)
            ) {
                Image(painter = painterResource(
                    if (boardDetailData.postLike) R.drawable.ic_heart_clicked else R.drawable.ic_heart
                ),
                    contentDescription = "좋아요 버튼",
                    modifier = Modifier
                        .padding(end = 6.dp)
                        .clickable {
                            viewModel.toggleHeart()
                        })
                Text(
                    text = boardDetailData.likeCount.toString(),
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
                    text = boardDetailData.commentCount.toString(),
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
            CommentSection(
                commentsInfo = boardDetailData.comments
            )
        }
    }
}

@Composable
fun CommentSection(
    commentsInfo: List<DetailComments>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        commentsInfo.forEach { commentInfo ->
            CommentItem(
                commentInfo = commentInfo
            )
        }
    }
}

@Composable
fun CommentItem(
    commentInfo: DetailComments
) {
    Column(
        modifier = Modifier.padding(vertical = 5.dp),
    ) {
        DetailComment(
            commentInfo  = commentInfo
        )

        Column() {
            commentInfo.replies.forEach { reply ->
                Row(
                    modifier = Modifier.padding(top = 9.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_community_reply),
                        contentDescription = "답글 아이콘"
                    )
                    Column() {
                        DetailReply(
                            commentId = commentInfo.commentId,
                            replyInfo = reply
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun DetailComment(
    commentInfo: DetailComments,
    viewModel: CommunityViewModel = hiltViewModel()
) {
    Row(
        modifier = Modifier.padding(bottom = 5.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = commentInfo.userProfileImg ?: R.drawable.ic_user_none,
            contentDescription = "유저 이미지",
            modifier = Modifier
                .size(20.dp)
                .padding(vertical = 2.dp)
        )
        Text(
            text = commentInfo.userName,
            modifier = Modifier.padding(start = 6.dp),
            color = TogedyTheme.colors.gray700,
            style = TogedyTheme.typography.body3B
        )

    }

    Text(
        text = commentInfo.content,
        modifier = Modifier.padding(bottom = 10.dp),
        color = TogedyTheme.colors.gray700,
        style = TogedyTheme.typography.body2M
    )

    Row(
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        Image(painter = painterResource(if (commentInfo.commentLike) R.drawable.ic_heart_clicked else R.drawable.ic_heart),
            contentDescription = "좋아요 버튼",
            modifier = Modifier
                .size(16.dp)
                .clickable {
                    viewModel.toggleCommentHeart(commentInfo.commentId)
                })
        Text(
            text = commentInfo.likeCount.toString(),
            modifier = Modifier.padding(start = 6.dp),
            color = TogedyTheme.colors.gray700,
            style = TogedyTheme.typography.body3M
        )
    }

    DetailDivider(modifier = Modifier.fillMaxWidth())
}

@Composable
fun DetailReply(
    commentId: Int,
    replyInfo: DetailReplies,
    viewModel: CommunityViewModel = hiltViewModel()
) {
    Row(
        modifier = Modifier.padding(bottom = 5.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = replyInfo.userProfileImg ?: R.drawable.ic_user_none,
            contentDescription = "유저 이미지",
            modifier = Modifier
                .size(20.dp)
                .padding(vertical = 2.dp)
        )
        Text(
            text = replyInfo.userName,
            modifier = Modifier.padding(start = 6.dp),
            color = TogedyTheme.colors.gray700,
            style = TogedyTheme.typography.body3B
        )

    }

    Text(
        text = replyInfo.content,
        modifier = Modifier.padding(bottom = 10.dp),
        color = TogedyTheme.colors.gray700,
        style = TogedyTheme.typography.body2M
    )

    Row(
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        Image(painter = painterResource(if (replyInfo.commentLike) R.drawable.ic_heart_clicked else R.drawable.ic_heart),
            contentDescription = "좋아요 버튼",
            modifier = Modifier
                .size(16.dp)
                .clickable {
                    viewModel.toggleReplyHeart(commentId, replyInfo.commentId)
                })
        Text(
            text = replyInfo.likeCount.toString(),
            modifier = Modifier.padding(start = 6.dp),
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
        modifier = modifier, thickness = 1.dp, color = TogedyTheme.colors.gray300
    )
}

@Composable
fun CommunityDetailImages(
    postImages: List<String>
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(postImages.size) { index ->
            CommunityDetailImage(postImages[index])
        }
    }
}

@Composable
fun CommunityDetailImage(image: String) {
    AsyncImage(
        model = "https://cdn.pixabay.com/photo/2021/10/27/19/09/cat-6748193_1280.jpg",
        contentDescription = "게시글 사진",
        modifier = Modifier
            .width(160.dp)
            .aspectRatio(1f),
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
fun CommunityDetailPreviewScreen() {
    CommunityDetailScreen(
        postId = 1, viewModel = viewModel()
    )
}