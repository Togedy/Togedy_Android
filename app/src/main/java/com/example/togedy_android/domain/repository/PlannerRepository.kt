package com.example.togedy_android.domain.repository

import com.example.togedy_android.domain.model.planner.NewStudyGoal
import com.example.togedy_android.domain.model.planner.NewStudyTageItem
import com.example.togedy_android.domain.model.planner.StudyGoal
import com.example.togedy_android.domain.model.planner.StudyGoalDate
import com.example.togedy_android.domain.model.planner.StudyGoalId
import com.example.togedy_android.domain.model.planner.StudyTagId
import com.example.togedy_android.domain.model.planner.StudyTagItem

interface PlannerRepository {
    suspend fun getStudyGoal(request: StudyGoalDate): Result<StudyGoal>
    suspend fun postStudyGoal(newStudyGoal: NewStudyGoal): Result<StudyGoalId>

    suspend fun getStudyTagList(): Result<List<StudyTagItem>>
    suspend fun postStudyTag(request: NewStudyTageItem): Result<StudyTagId>
    suspend fun putStudyTag(tagId: Int, request: NewStudyTageItem): Result<StudyTagId>
}