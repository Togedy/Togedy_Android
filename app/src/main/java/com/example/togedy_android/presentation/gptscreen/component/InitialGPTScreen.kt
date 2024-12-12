package com.example.togedy_android.presentation.gptscreen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.R

@Composable
fun InitialGPTScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 10.dp)
    ) {
        Spacer(Modifier.weight(1f))

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "무엇이 궁금하신가요?",
                style = TogedyTheme.typography.body1B,
                color = TogedyTheme.colors.black
            )

            Spacer(Modifier.width(15.dp))

            Image(
                painter = painterResource(R.drawable.ic_logo_face),
                contentDescription = "",
                modifier = Modifier.size(50.dp)
            )
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = "입시에 대한 모든 것 물어보세요!",
            style = TogedyTheme.typography.headline2B,
            color = TogedyTheme.colors.black
        )

        Spacer(Modifier.weight(1f))

        RecommendKeyword()

        Spacer(Modifier.weight(1f))
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RecommendKeyword() {
    val keywordList = listOf(
        "입시",
        "입시 설명회",
        "수능까지",
        "원서 마감일",
        "편입",
        "인강추천",
        "건국대",
        "대학별 순위"
    )

    Column {
        Text(
            text = "인기 키워드",
            style = TogedyTheme.typography.headline3B,
            color = TogedyTheme.colors.yellow500
        )
        Spacer(Modifier.height(12.dp))


        FlowRow(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            keywordList.forEach { keyword ->
                KeywordBlock(keyword = keyword)
            }
        }
    }
}


@Composable
fun KeywordBlock(keyword: String) {
    Box(
        modifier = Modifier
            .border(
                width = 1.5.dp,
                color = TogedyTheme.colors.yellowMain,
                shape = RoundedCornerShape(size = 30.dp)
            )
            .padding(horizontal = 20.dp, vertical = 4.dp)
    ) {
        Text(
            text = keyword,
            style = TogedyTheme.typography.body2M,
            color = TogedyTheme.colors.black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InitialGPTScreenPreview() {
    InitialGPTScreen()
}