package com.example.togedy_android.data.repositoryImpl

import com.example.togedy_android.data.mapper.todata.toData
import com.example.togedy_android.data.mapper.todomain.toDomain
import com.example.togedy_android.data.remote.datasource.CommunityRemoteDataSource
import com.example.togedy_android.data.util.handleBaseResponse
import com.example.togedy_android.domain.model.BoardList
import com.example.togedy_android.domain.model.BoardWriting
import com.example.togedy_android.domain.model.BoardWritingId
import com.example.togedy_android.domain.repository.CommunityRepository
import javax.inject.Inject

class CommunityRepositoryImpl @Inject constructor(
    private val communityRemoteDataSource: CommunityRemoteDataSource
) : CommunityRepository {
    override suspend fun getBoardList(boardType: String): Result<List<BoardList>> {
        return runCatching {
            communityRemoteDataSource.getBoardList(boardType = boardType)
                .handleBaseResponse().getOrThrow().toDomain()
        }
    }

    override suspend fun postBoardWriting(boardType: String, boardWriting: BoardWriting): Result<BoardWritingId> {
        return runCatching {
             communityRemoteDataSource.postBoardWriting(boardType = boardType, boardWritingRequestDto = boardWriting.toData())
                .handleBaseResponse().getOrThrow().toDomain()
        }
    }
}
