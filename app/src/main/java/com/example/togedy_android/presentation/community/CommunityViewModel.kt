package com.example.togedy_android.presentation.community

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togedy_android.domain.repository.CommunityRepository
import com.example.togedy_android.presentation.community.state.BoardListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val communityRepository: CommunityRepository
): ViewModel() {

    private val _boardListState = MutableStateFlow<BoardListState>(BoardListState.Idle)
    val boardListState: StateFlow<BoardListState> = _boardListState
    fun getBoardList(boardType: String) {
        _boardListState.value = BoardListState.Loading
        viewModelScope.launch {
            val result = communityRepository.getBoardList(boardType)
            result.fold(
                onSuccess = { boardList ->
                    _boardListState.value = BoardListState.Success(boardList)
                    Log.d("boardListState", boardListState.toString())
                },
                onFailure = { throwable ->
                    _boardListState.value = BoardListState.Failure(throwable.message ?: "Unknown error")
                    Log.d("boardListStateFailure", throwable.message.toString())
                }
            )
        }

    }
}