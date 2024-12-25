package com.example.togedy_android.presentation.calendar.univDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.component.TopBarBasic
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import com.example.togedy_android.domain.type.WritingType
import kotlinx.coroutines.launch

@Composable
fun UnivDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: UnivDetailViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState { 3 }
    val tabTitles = listOf("수시", "정시", "편입")
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = TogedyTheme.colors.white)
            .padding(horizontal = 20.dp)
    ) {
        TopBarBasic(
            leftButtonIcon = R.drawable.ic_chevron_left,
            title = WritingType.KONKUK_UNIV.type,
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

        HorizontalPager(state = pagerState) { page ->
            when (page) {
                0 -> UnivDetailPager()
                1 -> UnivDetailPager()
                2 -> UnivDetailPager()
            }
        }
    }
}

@Composable
fun UnivDetailPager() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp)
    ) {
        item {
            SelectTitle(
                text = "전체 선택"
            )
        }

        item {
            SelectTitleDate(
                text = "원서 접수",
                startDate = "2024.09.09 10:00",
                endDate = "2024.09.13 17:00"
            )
        }

        item {
            SelectTitleDate(
                text = "서류제출",
                startDate = "2024.09.09 10:00",
                endDate = "2024.09.12 17:00"
            )
        }

        item {
            SelectTitle(
                text = "온라인 입력"
            )
        }

        item {
            SelectSubTitleDate(
                startSub = "학생부종합전형",
                endSub = "학생생활 기록부 대체서식 입력",
                startDate = "2024.09.09 10:00",
                endDate = "2024.09.13 17:00",
                isStartDate = true
            )
        }

        item {
            SelectSubTitleDate(
                startSub = "학생부교과",
                endSub = "학교장 추천 명단 입력",
                startDate = "2024.09.19 09:00",
                endDate = "2024.09.25 18:00",
                isStartDate = true
            )
        }

        item {
            SelectTitle(
                text = "1단계 합격자 발표"
            )
        }

        item {
            SelectSubTitleDate(
                startSub = "실기/실적(KU연기우수자, KU체육특기자)",
                endSub = "",
                startDate = "",
                endDate = "2024.10.11 14:00 예정",
                isStartDate = false
            )
        }

        item {
            SelectSubTitleDate(
                startSub = "학생부종합(KU자기추천, 특수교육대상자)",
                endSub = "",
                startDate = "",
                endDate = "2024.11.15 14:00 예정",
                isStartDate = false
            )
        }
    }
}

@Composable
fun SelectTitle(
    text: String
) {
    Row(
        modifier = Modifier.padding(vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_uncheck_circle),
            modifier = Modifier.size(18.dp),
            contentDescription = "체크 버튼"
        )
        Text(
            text = text,
            modifier = Modifier.padding(start = 10.dp),
            style = TogedyTheme.typography.body2B
        )
    }
}

@Composable
fun SelectTitleDate(
    text: String,
    startDate: String,
    endDate: String
) {
    Column(
        modifier = Modifier.padding(vertical = 15.dp)
    ) {
        Row(
        ) {
            Image(
                painter = painterResource(R.drawable.ic_uncheck_circle),
                modifier = Modifier.size(18.dp),
                contentDescription = "체크 버튼"
            )
            Text(
                text = text,
                modifier = Modifier.padding(start = 10.dp),
                style = TogedyTheme.typography.body2B
            )
        }

        Row(
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Text(
                text = startDate,
                color = TogedyTheme.colors.gray600,
                style = TogedyTheme.typography.body2M
            )
            Text(
                text = "-",
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = endDate,
                color = TogedyTheme.colors.black,
                style = TogedyTheme.typography.body2M
            )
        }
    }
}

@Composable
fun SelectSubTitleDate(
    startSub: String,
    endSub: String,
    startDate: String,
    endDate: String,
    isStartDate: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_check_circle),
            modifier = Modifier.size(18.dp),
            contentDescription = "체크 버튼"
        )
        Column(
            modifier = Modifier.padding(vertical = 15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = startSub,
                    color = TogedyTheme.colors.gray600,
                    style = TogedyTheme.typography.body3M
                )
                Text(
                    text = endSub,
                    color = TogedyTheme.colors.gray600,
                    style = TogedyTheme.typography.body3M
                )
            }
            Row() {
                Text(
                    text = startDate,
                    color = TogedyTheme.colors.gray600,
                    style = TogedyTheme.typography.body2M
                )
                if (isStartDate) {
                    Text(
                        text = "-",
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
                Text(
                    text = endDate,
                    color = TogedyTheme.colors.black,
                    style = TogedyTheme.typography.body2M
                )
            }
        }

    }
}

@Preview
@Composable
fun UnivDetailScreenPreview() {
    UnivDetailScreen()
}