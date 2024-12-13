package com.example.togedy_android.presentation.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.togedy_android.R
import com.example.togedy_android.core.design_system.component.TopBarBasic
import com.example.togedy_android.core.design_system.component.WritingContentTextField
import com.example.togedy_android.core.design_system.theme.TogedyTheme
import kotlinx.coroutines.launch

@Composable
fun UnivListScreen(
    viewModel: UnivListViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState { 2 }
    val tabTitles = listOf("전체", "저장한 대학")
    val coroutineScope = rememberCoroutineScope()
    val allUnivGroups = viewModel.univGroups.collectAsState().value
    val savedUnivGroups = viewModel.savedUnivGroups.collectAsState().value

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
            when (page) {
                0 -> UnivListPager(univGroups = allUnivGroups)
                1 -> UnivListPager(univGroups = savedUnivGroups)
            }
        }
    }
}

@Composable
fun UnivListPager(univGroups: Map<Char, List<String>>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp)
    ) {
        univGroups.forEach { (initial, universities) ->
            item {
                Text(
                    text = initial.toString(),
                    style = TogedyTheme.typography.headline3B,
                    color = TogedyTheme.colors.black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
            }

            itemsIndexed(universities) { _, university ->
                Text(
                    text = university,
                    style = TogedyTheme.typography.body2R,
                    color = TogedyTheme.colors.gray500,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp)
                )
            }
        }
    }
}



@Preview
@Composable
fun UnivListScreenPreview() {
    UnivListScreen()
}
