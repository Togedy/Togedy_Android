package com.example.togedy_android.domain.repository

import com.example.togedy_android.domain.model.planner.NewStudyGoal
import com.example.togedy_android.domain.model.planner.StudyGoal
import com.example.togedy_android.domain.model.planner.StudyGoalDate
import com.example.togedy_android.domain.model.planner.StudyGoalId

interface PlannerRepository {
    suspend fun getStudyGoal(request: StudyGoalDate): Result<StudyGoal>
    suspend fun postStudyGoal(newStudyGoal: NewStudyGoal): Result<StudyGoalId>
}