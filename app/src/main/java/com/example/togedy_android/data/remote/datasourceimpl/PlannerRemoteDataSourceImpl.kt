package com.example.togedy_android.data.remote.datasourceimpl

import com.example.togedy_android.data.remote.datasource.PlannerRemoteDataSource
import com.example.togedy_android.data.remote.model.base.BaseResponse
import com.example.togedy_android.data.remote.model.request.StudyGoalDateRequestDto
import com.example.togedy_android.data.remote.model.request.StudyGoalRequestDto
import com.example.togedy_android.data.remote.model.response.StudyGoalIdResponseDto
import com.example.togedy_android.data.remote.model.response.StudyGoalResponseDto
import com.example.togedy_android.data.remote.service.PlannerService
import javax.inject.Inject

class PlannerRemoteDataSourceImpl @Inject constructor(
    private val plannerService: PlannerService
) : PlannerRemoteDataSource {
    override suspend fun getStudyGoal(request: StudyGoalDateRequestDto): BaseResponse<StudyGoalResponseDto> =
        plannerService.getStudyGoal(request)


    override suspend fun postStudyGoal(request: StudyGoalRequestDto): BaseResponse<StudyGoalIdResponseDto> =
        plannerService.postStudyGoal(request)


}