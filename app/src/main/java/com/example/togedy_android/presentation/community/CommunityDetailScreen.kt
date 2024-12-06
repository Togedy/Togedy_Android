package com.example.togedy_android.presentation.community

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.example.togedy_android.core.design_system.component.TopBarBasic
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.theme.TogedyTheme

@Composable
fun CommunityDetailScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = TogedyTheme.colors.white)
            .padding(horizontal = 18.dp, vertical = 16.dp)
    ) {
        TopBarBasic(
            leftButtonIcon = R.drawable.ic_chevron_left,
            title = "",
            onLeftButtonClicked = { }
        )

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            thickness = 1.dp,
            color = TogedyTheme.colors.gray300
        )

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

        CommunityDetailImages()

        Text(
            text = "건대 근처 맛집 팝니다",
            modifier = Modifier.padding(bottom = 16.dp),
            color = TogedyTheme.colors.black,
            style = TogedyTheme.typography.headline3B
        )

        Text(
            text = "건대 근처 학원 다니면서 맛집 정말 많이 다녀봤고, 제가 맛잘알로써 진짜 맛있는 집들만 정리해뒀습니다. \n" +
                    "\n" +
                    "가격은 3000원!!\n" +
                    "편하게 연락주세요",
            color = TogedyTheme.colors.gray800,
            style = TogedyTheme.typography.body1M,
            modifier = Modifier.padding(bottom = 38.dp)
        )

        Row(
            modifier = Modifier.padding(bottom = 14.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_heart),
                contentDescription = "좋아요 버튼",
                modifier = Modifier.padding(end = 16.dp)
            )
            Image(
                painter = painterResource(R.drawable.ic_message),
                contentDescription = "댓글 버튼"
            )
        }

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 12.dp),
            thickness = 1.dp,
            color = TogedyTheme.colors.gray300
        )

        Text(text = stringResource(R.string.community_detail_comment),
            color = TogedyTheme.colors.gray300,
            style = TogedyTheme.typography.body3B)
    }
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
    CommunityDetailScreen()
}