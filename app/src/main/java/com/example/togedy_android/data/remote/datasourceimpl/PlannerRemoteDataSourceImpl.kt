package com.example.togedy_android.data.remote.datasourceimpl

import com.example.togedy_android.data.remote.datasource.PlannerRemoteDataSource
import com.example.togedy_android.data.remote.model.base.BaseResponse
import com.example.togedy_android.data.remote.model.planner.request.StudyGoalDateRequestDto
import com.example.togedy_android.data.remote.model.planner.request.StudyGoalRequestDto
import com.example.togedy_android.data.remote.model.planner.request.StudyTagItemRequestDto
import com.example.togedy_android.data.remote.model.planner.resposne.StudyGoalIdResponseDto
import com.example.togedy_android.data.remote.model.planner.resposne.StudyGoalResponseDto
import com.example.togedy_android.data.remote.model.planner.resposne.StudyTagIdResponseDto
import com.example.togedy_android.data.remote.model.planner.resposne.StudyTagItemResponseDto
import com.example.togedy_android.data.remote.service.PlannerService
import javax.inject.Inject

class PlannerRemoteDataSourceImpl @Inject constructor(
    private val plannerService: PlannerService
) : PlannerRemoteDataSource {
    override suspend fun getStudyGoal(request: StudyGoalDateRequestDto): BaseResponse<StudyGoalResponseDto> =
        plannerService.getStudyGoal(request)

    override suspend fun postStudyGoal(request: StudyGoalRequestDto): BaseResponse<StudyGoalIdResponseDto> =
        plannerService.postStudyGoal(request)


    override suspend fun getStudyTagList(): BaseResponse<List<StudyTagItemResponseDto>> =
        plannerService.getStudyTag()

    override suspend fun postStudyTag(request: StudyTagItemRequestDto): BaseResponse<StudyTagIdResponseDto> =
        plannerService.postStudyTag(request)

    override suspend fun putStudyTag(tagId: Int, request: StudyTagItemRequestDto): BaseResponse<StudyTagIdResponseDto> =
        plannerService.putStudyTag(tagId = tagId, request =  request)
}