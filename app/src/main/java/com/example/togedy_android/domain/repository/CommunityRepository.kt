package com.example.togedy_android.domain.repository

import com.example.togedy_android.domain.model.BoardList
import com.example.togedy_android.domain.model.BoardWriting
import com.example.togedy_android.domain.model.BoardWritingId

interface CommunityRepository {
    suspend fun getBoardList(boardType: String): Result<ArrayList<BoardList>>
    suspend fun postBoardWriting(boardType: String, boardWriting: BoardWriting): Result<BoardWritingId>
}