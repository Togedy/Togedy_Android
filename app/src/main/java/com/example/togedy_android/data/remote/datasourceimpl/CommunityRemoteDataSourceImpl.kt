package com.example.togedy_android.data.remote.datasourceimpl

import com.example.togedy_android.data.remote.datasource.CommunityRemoteDataSource
import com.example.togedy_android.data.remote.model.base.BaseResponse
import com.example.togedy_android.data.remote.model.request.BoardWritingRequestDto
import com.example.togedy_android.data.remote.model.response.BoardDetailResponseDto
import com.example.togedy_android.data.remote.model.response.BoardListResponseDto
import com.example.togedy_android.data.remote.model.response.BoardWritingResponseDto
import com.example.togedy_android.data.remote.service.CommunityService
import javax.inject.Inject

class CommunityRemoteDataSourceImpl @Inject constructor(
    private val communityService: CommunityService
) : CommunityRemoteDataSource{
    override suspend fun getBoardList(boardType: String, univName: String?): BaseResponse<List<BoardListResponseDto>> =
        communityService.getBoardList(boardType)


    override suspend fun postBoardWriting(
        boardType: String,
        boardWritingRequestDto: BoardWritingRequestDto
    ): BaseResponse<BoardWritingResponseDto> =
        communityService.postBoardWriting(boardType, boardWritingRequestDto)

    override suspend fun getBoardDetail(postId: Int): BaseResponse<BoardDetailResponseDto> =
        communityService.getBoardDetail(postId = postId)
}