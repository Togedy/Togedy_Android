package com.example.togedy_android.data.remote.datasource

import com.example.togedy_android.data.remote.model.base.BaseResponse
import com.example.togedy_android.data.remote.model.planner.request.StudyGoalDateRequestDto
import com.example.togedy_android.data.remote.model.planner.request.StudyGoalRequestDto
import com.example.togedy_android.data.remote.model.planner.request.StudyTagItemRequestDto
import com.example.togedy_android.data.remote.model.planner.resposne.StudyGoalIdResponseDto
import com.example.togedy_android.data.remote.model.planner.resposne.StudyGoalResponseDto
import com.example.togedy_android.data.remote.model.planner.resposne.StudyTagIdResponseDto
import com.example.togedy_android.data.remote.model.planner.resposne.StudyTagItemResponseDto

interface PlannerRemoteDataSource {
    suspend fun getStudyGoal(request: StudyGoalDateRequestDto): BaseResponse<StudyGoalResponseDto>
    suspend fun postStudyGoal(request: StudyGoalRequestDto): BaseResponse<StudyGoalIdResponseDto>

    suspend fun getStudyTagList() : BaseResponse<List<StudyTagItemResponseDto>>
    suspend fun postStudyTag(request: StudyTagItemRequestDto): BaseResponse<StudyTagIdResponseDto>
    suspend fun putStudyTag(tagId: Int, request: StudyTagItemRequestDto): BaseResponse<StudyTagIdResponseDto>
}