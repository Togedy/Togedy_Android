package com.example.togedy_android.data.remote.service

import com.example.togedy_android.data.remote.model.base.BaseResponse
import com.example.togedy_android.data.remote.model.request.StudyGoalDateRequestDto
import com.example.togedy_android.data.remote.model.request.StudyGoalRequestDto
import com.example.togedy_android.data.remote.model.response.StudyGoalIdResponseDto
import com.example.togedy_android.data.remote.model.response.StudyGoalResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface PlannerService {
    @POST("planner/studyGoal")
    suspend fun getStudyGoal(
        @Body request: StudyGoalDateRequestDto
    ): BaseResponse<StudyGoalResponseDto>

    @POST("planner/studyGoal/set")
    suspend fun postStudyGoal(
        @Body request: StudyGoalRequestDto
    ): BaseResponse<StudyGoalIdResponseDto>
}