package com.example.togedy_android.presentation.community.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ForumList(
    viewModel: ViewModel = viewModel()
) {
    LazyColumn(){
//        items()
    }
}

@Preview
@Composable
fun ForumListPreview(){
    ForumList()
}