package com.example.togedy_android.presentation.community.state

import com.example.togedy_android.domain.model.BoardList

sealed class BoardListState {
    data object Idle: BoardListState()
    data object Loading: BoardListState()
    data class Success(val data: List<BoardList>): BoardListState()
    data class Failure(val message: String): BoardListState()
}