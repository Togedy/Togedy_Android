package com.example.togedy_android.presentation.ui.screens.community.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.R
import com.example.togedy_android.core.component.TogedyTitle
import com.example.togedy_android.ui.theme.TogedyTheme

@Composable
fun HomeBulletinBoard(
    onBulletinBoardMoreClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 10.dp)
                .clickable {
                    onBulletinBoardMoreClicked()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            TogedyTitle(text = "자유게시판")
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_chevron_right),
                contentDescription = "더보기 버튼",
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        HomeBulletinBoardItem()
        HomeBulletinBoardItem()
        HomeBulletinBoardItem()
        HorizontalDivider(
            modifier = Modifier
                .height(1.dp),
            color = Color(0xFFDADADA)
        )
    }
}

@Composable
fun HomeBulletinBoardItem() {
    Column {
        HorizontalDivider(
            modifier = Modifier
                .height(1.dp),
            color = Color(0xFFDADADA)
        )
        Column(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
        ) {
            Text(
                text = "제목이 드렁가는 부분",
                style = TogedyTheme.typography.body2B,
                color = TogedyTheme.colors.gray700
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용",
                style = TogedyTheme.typography.body3M,
                color = TogedyTheme.colors.gray300,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Preview
@Composable
fun HomeBulletinBoardPreview() {
    HomeBulletinBoard(
        onBulletinBoardMoreClicked = { }
    )
}