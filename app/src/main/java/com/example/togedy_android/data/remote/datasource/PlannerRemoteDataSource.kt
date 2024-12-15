package com.example.togedy_android.data.remote.datasource

import com.example.togedy_android.data.remote.model.base.BaseResponse
import com.example.togedy_android.data.remote.model.request.StudyGoalDateRequestDto
import com.example.togedy_android.data.remote.model.request.StudyGoalRequestDto
import com.example.togedy_android.data.remote.model.response.StudyGoalIdResponseDto
import com.example.togedy_android.data.remote.model.response.StudyGoalResponseDto

interface PlannerRemoteDataSource {
    suspend fun getStudyGoal(request: StudyGoalDateRequestDto): BaseResponse<StudyGoalResponseDto>
    suspend fun postStudyGoal(request: StudyGoalRequestDto): BaseResponse<StudyGoalIdResponseDto>
}