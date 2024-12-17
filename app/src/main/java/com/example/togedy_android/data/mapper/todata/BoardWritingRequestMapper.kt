package com.example.togedy_android.data.mapper.todata

import com.example.togedy_android.data.remote.model.request.BoardWritingRequestDto
import com.example.togedy_android.domain.model.BoardWriting

fun BoardWriting.toData(): BoardWritingRequestDto = BoardWritingRequestDto(
    title = this.title,
    content = this.content,
    postImages = this.postImages
)