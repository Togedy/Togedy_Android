package com.example.togedy_android.presentation.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.togedy_android.core.design_system.component.TopBarBasic
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import kotlinx.coroutines.launch
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.component.WritingContentTextField

@Composable
fun UnivListScreen() {
    val pagerState = rememberPagerState { 2 }
    val tabTitles = listOf("전체", "저장한 대학")
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = TogedyTheme.colors.white)
            .padding(horizontal = 20.dp)
    ) {
        TopBarBasic(
            leftButtonIcon = R.drawable.ic_chevron_left,
            title = "대학별 일정보기",
            onLeftButtonClicked = { },
            modifier = Modifier.padding(vertical = 15.dp)
        )
        Row(
            modifier = Modifier.padding(vertical = 15.dp),
        ) {
            tabTitles.forEachIndexed { index, title ->
                Text(
                    text = title,
                    modifier = Modifier
                        .clickable {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                        .padding(end = 16.dp),
                    style = TogedyTheme.typography.headline3B,
                    color = if (pagerState.currentPage == index) TogedyTheme.colors.black else TogedyTheme.colors.gray200
                )
            }
        }

        WritingContentTextField(
            value = "hi",
            onTextFieldChange = { },
            placeholder = "no"
        )

        HorizontalPager(state = pagerState) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        when (page) {
                            0 -> Color.Red
                            1 -> Color.Green
                            else -> Color.Gray
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Page: $page", color = Color.White, fontSize = 24.sp)
            }
        }
    }
}


@Preview
@Composable
fun UnivListScreenPreview() {
    UnivListScreen()
}
