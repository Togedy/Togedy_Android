package com.example.togedy_android.data.remote.datasource

import com.example.togedy_android.data.remote.model.base.BaseResponse
import com.example.togedy_android.data.remote.model.request.BoardWritingRequestDto
import com.example.togedy_android.data.remote.model.response.BoardListResponseDto
import com.example.togedy_android.data.remote.model.response.BoardWritingResponseDto

interface CommunityRemoteDataSource {
    suspend fun getBoardList(boardType: String): BaseResponse<List<BoardListResponseDto>>
    suspend fun postBoardWriting(boardType: String, boardWritingRequestDto: BoardWritingRequestDto):
            BaseResponse<BoardWritingResponseDto>
}