package com.example.togedy_android.data.repositoryImpl

import com.example.togedy_android.data.mapper.todata.planner.toData
import com.example.togedy_android.data.mapper.todomain.planner.toDomain
import com.example.togedy_android.data.remote.datasource.PlannerRemoteDataSource
import com.example.togedy_android.data.util.handleBaseResponse
import com.example.togedy_android.domain.model.planner.NewStudyGoal
import com.example.togedy_android.domain.model.planner.NewStudyTageItem
import com.example.togedy_android.domain.model.planner.StudyGoal
import com.example.togedy_android.domain.model.planner.StudyGoalDate
import com.example.togedy_android.domain.model.planner.StudyGoalId
import com.example.togedy_android.domain.model.planner.StudyTagId
import com.example.togedy_android.domain.model.planner.StudyTagItem
import com.example.togedy_android.domain.repository.PlannerRepository
import javax.inject.Inject

class PlannerRepositoryImpl @Inject constructor(
    private val plannerRemoteDataSource: PlannerRemoteDataSource
) : PlannerRepository {
    override suspend fun getStudyGoal(request: StudyGoalDate): Result<StudyGoal> {
        return runCatching {
            plannerRemoteDataSource.getStudyGoal(request = request.toData())
                .handleBaseResponse().getOrThrow().toDomain()
        }
    }

    override suspend fun postStudyGoal(newStudyGoal: NewStudyGoal): Result<StudyGoalId> {
        return runCatching {
            plannerRemoteDataSource.postStudyGoal(request = newStudyGoal.toData())
                .handleBaseResponse().getOrThrow().toDomain()
        }
    }

    override suspend fun getStudyTagList() : Result<List<StudyTagItem>> {
        return runCatching {
            plannerRemoteDataSource.getStudyTagList()
                .handleBaseResponse().getOrThrow().toDomain()
        }
    }

    override suspend fun postStudyTag(request: NewStudyTageItem): Result<StudyTagId> {
        return runCatching {
            plannerRemoteDataSource.postStudyTag(request = request.toData())
                .handleBaseResponse().getOrThrow().toDomain()
        }
    }

    override suspend fun putStudyTag(
        tagId: Int,
        request: NewStudyTageItem
    ): Result<StudyTagId> {
        return runCatching {
            plannerRemoteDataSource.putStudyTag(
                tagId = tagId,
                request = request.toData()
            ).handleBaseResponse().getOrThrow().toDomain()
        }
    }
}