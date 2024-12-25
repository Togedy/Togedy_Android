package com.example.togedy_android.presentation.community.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.togedy_android.core.design_system.theme.TogedyTheme

@Composable
fun CommunityForumListItem(
    postId: Int,
    title: String,
    date: String,
    type: String,
    content: String,
    image: String?,
    navigateToCommunityDetail: (Int) -> Unit
) {
    Column(
        modifier = Modifier.clickable {
            navigateToCommunityDetail(postId)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .background(color = TogedyTheme.colors.white)
                .padding(horizontal = 6.dp, vertical = 8.dp)
        ) {
            if (!image.isNullOrEmpty()) {
                Card(
                    modifier = Modifier
                        .padding(end = 9.dp)
                        .size(70.dp)
                        .aspectRatio(1f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Image(
                        painter = rememberImagePainter(data = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj_BCAlUfURJQQFPlTMmTnARzSNewFJU0GMg&s"),
                        contentDescription = "게시판 리스트 사진",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = title,
                    modifier = Modifier.padding(
                        bottom = if (type != "market") 2.dp else 3.dp
                    ),
                    color = TogedyTheme.colors.gray700,
                    style = TogedyTheme.typography.body2B,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                if (type != "market") {
                    Text(
                        text = date,
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = TogedyTheme.colors.gray300,
                        style = TogedyTheme.typography.caption
                    )
                }

                Text(
                    text = content,
                    color = TogedyTheme.colors.gray300,
                    style = TogedyTheme.typography.body3M,
                    maxLines = if (type == "market") 3 else 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = TogedyTheme.colors.gray300
        )
    }
}

@Preview
@Composable
fun CommunityForumListItemPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(TogedyTheme.colors.white)
    ) {
        // 사진 있는 경우
        CommunityForumListItem(
            postId = 5,
            title = "건대 맛집 리스트 팝니다",
            date = "2024.12.05",
            content = "건대 근처 학원 다니면서 맛집 정말 많이 다녀봤고, 제가 맛잘알로써 진짜 맛있는 집들만 정리해뒀습니다. 가격은 3000원!! 편하게 연락주세요",
            type = "market",
            image = "https://cdn.pixabay.com/photo/2021/10/27/19/09/cat-6748193_1280.jpg",
            navigateToCommunityDetail = { }
        )

        // 사진 없는 경우
        CommunityForumListItem(
            postId = 5,
            title = "건대 맛집 리스트 팝니다",
            date = "2024.12.05",
            content = "건대 근처 학원 다니면서 맛집 정말 많이 다녀봤고, 제가 맛잘알로써 진짜 맛있는 집들만 정리해뒀습니다. 가격은 3000원!! 편하게 연락주세요",
            type = "free",
            image = null,
            navigateToCommunityDetail = { }
        )
    }
}
