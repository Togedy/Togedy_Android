package com.example.togedy_android.data.mapper.todomain

import com.example.togedy_android.data.remote.model.response.BoardListResponseDto
import com.example.togedy_android.domain.model.BoardList

fun ArrayList<BoardListResponseDto>.toDomain(): ArrayList<BoardList> {
    return this.map { boardListResponseDto ->
        BoardList(
            title = boardListResponseDto.title,
            createdAt = boardListResponseDto.createdAt,
            content = boardListResponseDto.content,
            postImages = boardListResponseDto.postImages
        )
    } as ArrayList<BoardList>
}