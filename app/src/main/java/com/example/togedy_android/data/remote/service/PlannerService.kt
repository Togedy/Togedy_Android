package com.example.togedy_android.data.remote.service

import com.example.togedy_android.data.remote.model.base.BaseResponse
import com.example.togedy_android.data.remote.model.planner.DateRequestDto
import com.example.togedy_android.data.remote.model.planner.request.StudyGoalRequestDto
import com.example.togedy_android.data.remote.model.planner.request.StudyPlanRequestDto
import com.example.togedy_android.data.remote.model.planner.request.StudyTagItemRequestDto
import com.example.togedy_android.data.remote.model.planner.resposne.StudyGoalIdResponseDto
import com.example.togedy_android.data.remote.model.planner.resposne.StudyGoalResponseDto
import com.example.togedy_android.data.remote.model.planner.resposne.StudyPlanIdResponseDto
import com.example.togedy_android.data.remote.model.planner.resposne.StudyPlanResponseDto
import com.example.togedy_android.data.remote.model.planner.resposne.StudyPlanStatusResponseDto
import com.example.togedy_android.data.remote.model.planner.resposne.StudyTagIdResponseDto
import com.example.togedy_android.data.remote.model.planner.resposne.StudyTagItemResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PlannerService {
    // 오늘의 목표량
    @POST("planner/studyGoal")
    suspend fun getStudyGoal(
        @Body request: DateRequestDto
    ): BaseResponse<StudyGoalResponseDto>

    @POST("planner/studyGoal/set")
    suspend fun postStudyGoal(
        @Body request: StudyGoalRequestDto
    ): BaseResponse<StudyGoalIdResponseDto>

    // 공부 태그
    @GET("planner/studyTag")
    suspend fun getStudyTag(
    ): BaseResponse<List<StudyTagItemResponseDto>>

    @POST("planner/studyTag")
    suspend fun postStudyTag(
        @Body request: StudyTagItemRequestDto
    ): BaseResponse<StudyTagIdResponseDto>

    @PUT("planner/studyTag/{tagId}")
    suspend fun putStudyTag(
        @Path("tagId") tagId: Int,
        @Body request: StudyTagItemRequestDto
    ): BaseResponse<StudyTagIdResponseDto>

    // 스터디 플랜
    @POST("planner/studyPlan")
    suspend fun getStudyPlanList(
        @Body request: DateRequestDto
    ): BaseResponse<List<StudyPlanResponseDto>>

    @POST("planner/studyPlan/create")
    suspend fun postStudyPlan(
        @Body request: StudyPlanRequestDto
    ): BaseResponse<StudyPlanIdResponseDto>

    @PUT("planner/studyPlan/{studyPlanId}/status?=status")
    suspend fun putStudyPlanStatus(
        @Path("studyPlanId") studyPlanId: Int,
        @Query("status") status: String? = null
    ): BaseResponse<StudyPlanStatusResponseDto>
}