package com.example.togedy_android.data.mapper.todomain.planner

import com.example.togedy_android.data.remote.model.planner.resposne.StudyPlanIdResponseDto
import com.example.togedy_android.domain.model.planner.StudyPlanId

fun StudyPlanIdResponseDto.toDomain(): StudyPlanId = StudyPlanId(
    studyPlanId = this.studyPlanId
)