package com.example.togedy_android.data.mapper.todomain

import com.example.togedy_android.data.remote.model.response.BoardWritingResponseDto
import com.example.togedy_android.domain.model.BoardWritingId

fun BoardWritingResponseDto.toDomain(): BoardWritingId = BoardWritingId(
    postId = this.postId,

)