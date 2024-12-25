package com.example.togedy_android.presentation.community.component

import android.util.Log
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
import com.example.togedy_android.core.design_system.component.TogedyTitle
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.domain.type.WritingType

@Composable
fun HomeBulletinBoard(
    onBulletinBoardMoreClicked: (String, String?) -> Unit,
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
                    onBulletinBoardMoreClicked("free", null)
                    Log.d("more", WritingType.BULLETIN_BOARD.type)
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
                text = "건국대학교 입시 떴다;;",
                style = TogedyTheme.typography.body2B,
                color = TogedyTheme.colors.gray700
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "건대 이번에 반영 비율 왜 이래?\n저번 반영 비율이 내가 알기로는 1단계 학생부(교과정량) 100%로 25배수를 선발하여, 2단계에서 실기 100%로 선발로 알고있는데\n",

                style = TogedyTheme.typography.body3M,
                color = TogedyTheme.colors.gray300,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}