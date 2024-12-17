package com.example.togedy_android.presentation.community.state

import com.example.togedy_android.domain.model.BoardDetail

sealed class BoardDetailState {
    data object Idle: BoardDetailState()
    data object Loading: BoardDetailState()
    data class Success(val data: BoardDetail): BoardDetailState()
    data class Failure(val message: String): BoardDetailState()
}