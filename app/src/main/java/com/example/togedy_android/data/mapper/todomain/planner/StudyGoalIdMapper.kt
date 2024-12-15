package com.example.togedy_android.data.mapper.todomain.planner

import com.example.togedy_android.data.remote.model.planner.resposne.StudyGoalIdResponseDto
import com.example.togedy_android.domain.model.planner.StudyGoalId

fun StudyGoalIdResponseDto.toDomain(): StudyGoalId = StudyGoalId(
    studyGoalId = this.studyGoalId
)