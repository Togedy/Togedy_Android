package com.example.togedy_android.presentation.community

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togedy_android.domain.model.BoardDetail
import com.example.togedy_android.domain.repository.CommunityRepository
import com.example.togedy_android.domain.type.WritingType
import com.example.togedy_android.presentation.community.state.BoardDetailState
import com.example.togedy_android.presentation.community.state.BoardListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val communityRepository: CommunityRepository
) : ViewModel() {

    private val _boardListState = MutableStateFlow<BoardListState>(BoardListState.Idle)
    val boardListState: StateFlow<BoardListState> = _boardListState

    private val _boardDetailState = MutableStateFlow<BoardDetailState>(BoardDetailState.Idle)
    val boardDetailState: StateFlow<BoardDetailState> = _boardDetailState

    private val _boardDetailData = MutableStateFlow<BoardDetail>(
        BoardDetail(
            title = "",
            createdAt = "",
            content = "",
            postImages = emptyList(),
            likeCount = 0,
            commentCount = 0,
            comments = emptyList(),
            postLike = false
        )
    )
    val boardDetailData: StateFlow<BoardDetail> = _boardDetailData

    fun getBoardType(boardName: String): WritingType? {
        return WritingType.entries.find { it.type == boardName }
    }

    fun getBoardPath(writingType: WritingType): String {
        return when (writingType) {
            WritingType.BULLETIN_BOARD -> "free"
            WritingType.MARKETPLACE -> "market"
            WritingType.STUDY_RECORD -> "study"
            else -> "univ"
        }
    }

    fun getBoardTitlePath(boardType: String): WritingType? {
        return when (boardType) {
            "free" -> WritingType.BULLETIN_BOARD
            "market" -> WritingType.MARKETPLACE
            "study" -> WritingType.STUDY_RECORD
            else -> null
        }
    }

    fun getBoardList(boardType: String, univName: String?) {
        _boardListState.value = BoardListState.Loading
        viewModelScope.launch {
            val result = communityRepository.getBoardList(boardType, univName)
            result.fold(
                onSuccess = { boardList ->
                    _boardListState.value = BoardListState.Success(boardList)
                    Log.d("boardListState", boardListState.toString())
                },
                onFailure = { throwable ->
                    _boardListState.value =
                        BoardListState.Failure(throwable.message ?: "Unknown error")
                    Log.d("boardListStateFailure", throwable.message.toString())
                }
            )
        }
    }

    fun getBoardDetail(postId: Int) {
        _boardDetailState.value = BoardDetailState.Loading
        viewModelScope.launch {
            val result = communityRepository.getBoardDetail(postId)
            result.fold(
                onSuccess = { boardDetail ->
                    _boardDetailData.value = boardDetail
                    _boardDetailState.value = BoardDetailState.Success(boardDetail)
                    Log.d("boardDetailState", boardDetailState.toString())
                },
                onFailure = { throwable ->
                    _boardDetailState.value =
                        BoardDetailState.Failure(throwable.message ?: "Unknown error")
                    Log.d("boardDetailStateFailure", throwable.message.toString())
                }
            )
        }
    }

    fun toggleHeart() {
        _boardDetailData.value = _boardDetailData.value.copy(
            postLike = !_boardDetailData.value.postLike,
            likeCount = if (_boardDetailData.value.postLike) {
                _boardDetailData.value.likeCount - 1
            } else {
                _boardDetailData.value.likeCount + 1
            }
        )
    }

    fun toggleCommentHeart(commentId: Int) {
        _boardDetailData.value = _boardDetailData.value.copy(
            comments = _boardDetailData.value.comments.map { comment ->
                if (comment.commentId == commentId) {
                    comment.copy(
                        commentLike = !comment.commentLike,
                        likeCount = if (comment.commentLike) comment.likeCount - 1 else comment.likeCount + 1
                    )
                } else comment
            }
        )
    }

    fun toggleReplyHeart(commentId: Int, replyId: Int) {
        _boardDetailData.value = _boardDetailData.value.copy(
            comments = _boardDetailData.value.comments.map { comment ->
                if (comment.commentId == commentId) {
                    comment.copy(
                        replies = comment.replies.map { reply ->
                            if (reply.commentId == replyId) {
                                reply.copy(
                                    commentLike = !reply.commentLike,
                                    likeCount = if (reply.commentLike) reply.likeCount - 1 else reply.likeCount + 1
                                )
                            } else reply
                        }
                    )
                } else comment
            }
        )
    }
}