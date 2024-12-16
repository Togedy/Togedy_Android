package com.example.togedy_android.presentation.calendar.univDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
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

    }
}

@Preview
@Composable
fun UnivDetailScreenPreview(){
    UnivDetailScreen()
}