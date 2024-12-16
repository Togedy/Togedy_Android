package com.example.togedy_android.domain.repository

import com.example.togedy_android.domain.model.planner.NewStudyGoal
import com.example.togedy_android.domain.model.planner.NewStudyTageItem
import com.example.togedy_android.domain.model.planner.StudyGoal
import com.example.togedy_android.domain.model.planner.Date
import com.example.togedy_android.domain.model.planner.NewStudyPlan
import com.example.togedy_android.domain.model.planner.StudyGoalId
import com.example.togedy_android.domain.model.planner.StudyPlanId
import com.example.togedy_android.domain.model.planner.StudyPlanItem
import com.example.togedy_android.domain.model.planner.StudyPlanStatus
import com.example.togedy_android.domain.model.planner.StudyTagId
import com.example.togedy_android.domain.model.planner.StudyTagItem

interface PlannerRepository {
    suspend fun getStudyGoal(request: Date): Result<StudyGoal>
    suspend fun postStudyGoal(newStudyGoal: NewStudyGoal): Result<StudyGoalId>

    suspend fun getStudyTagList(): Result<List<StudyTagItem>>
    suspend fun postStudyTag(request: NewStudyTageItem): Result<StudyTagId>
    suspend fun putStudyTag(tagId: Int, request: NewStudyTageItem): Result<StudyTagId>

    suspend fun getStudyPlanList(request: Date): Result<List<StudyPlanItem>>
    suspend fun postStudyPlan(request: NewStudyPlan): Result<StudyPlanId>
    suspend fun putStudyPlanStatus(studyPlanId: Int, status: String): Result<StudyPlanStatus>
}