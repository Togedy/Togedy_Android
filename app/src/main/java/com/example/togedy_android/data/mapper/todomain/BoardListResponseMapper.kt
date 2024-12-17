package com.example.togedy_android.data.mapper.todomain

import com.example.togedy_android.data.remote.model.response.BoardListResponseDto
import com.example.togedy_android.domain.model.BoardList

fun List<BoardListResponseDto>.toDomain(): List<BoardList> {
    return this.map { boardListResponseDto ->
        BoardList(
            postId = boardListResponseDto.postId,
            title = boardListResponseDto.title,
            createdAt = boardListResponseDto.createdAt,
            content = boardListResponseDto.content,
            postImages = boardListResponseDto.postImages ?: emptyList()
        )
    }
}