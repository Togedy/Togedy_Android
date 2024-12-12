package com.example.togedy_android.data.remote.service

import com.example.togedy_android.data.remote.model.base.BaseResponse
import com.example.togedy_android.data.remote.model.request.BoardWritingRequestDto
import com.example.togedy_android.data.remote.model.response.BoardDetailResponseDto
import com.example.togedy_android.data.remote.model.response.BoardListResponseDto
import com.example.togedy_android.data.remote.model.response.BoardWritingResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CommunityService {
    @GET("board/{boardType}")
    suspend fun getBoardList(
        @Path("boardType") boardType: String,
        @Query("univName") univName: String? = null
    ): BaseResponse<List<BoardListResponseDto>>

    @Multipart
    @POST("board/{boardType}/post")
    suspend fun postBoardWriting(
        @Path("boardType") boardType: String,
        @Body boardWriteRequestDto: BoardWritingRequestDto
    ): BaseResponse<BoardWritingResponseDto>

    @GET("board/post/{postId}")
    suspend fun getBoardDetail(
        @Path("postId") postId: Int
    ): BaseResponse<BoardDetailResponseDto>
}