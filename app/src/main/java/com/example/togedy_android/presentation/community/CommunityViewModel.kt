package com.example.togedy_android.presentation.community

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togedy_android.domain.model.BoardList
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

    private val _boardListData = MutableStateFlow(ArrayList<BoardList>())
    val boardListData: StateFlow<ArrayList<BoardList>> = _boardListData

    fun getBoardList(boardType: String){
        _boardListState.value = BoardListState.Loading
        viewModelScope.launch {
            val result = communityRepository.getBoardList(boardType = boardType)
            result.fold(
                onSuccess = { boardList ->
//                    _boardListData.value = boardList
                    // 더미 데이터 추가
                    val dummyBoardList = getDummyBoardList()
                    // 더미 데이터를 사용하여 테스트
                    _boardListData.value = dummyBoardList
                    
                    _boardListState.value = BoardListState.Success(boardList)
                },
                onFailure = { throwable ->
                    _boardListState.value = BoardListState.Failure(throwable.message ?: "알 수 없는 오류")
                    Log.e("SeatMapViewModel", "로드 실패: ${_boardListState.value}")

                }
            )
        }
    }

    private fun getDummyBoardList(): ArrayList<BoardList> {
        return arrayListOf(
            BoardList(
                title = "맛집 리스트 팝니다",
                createdAt = "2024.12.05",
                content = "건대 근처 맛집 정리해봤습니다. 가격은 3000원!!",
                postImages = listOf("1", "2")
            ),
            BoardList(
                title = "무료 강의 안내",
                createdAt = "2024.12.05",
                content = "건대 근처에서 무료 강의를 진행합니다.",
                postImages = listOf("3", "4")
            ),
            BoardList(
                title = "스터디 모집합니다",
                createdAt = "2024.12.06",
                content = "코딩 스터디 모집합니다. 관심 있는 분들 연락주세요.",
                postImages = listOf("5", "6")
            )
        )
    }
}