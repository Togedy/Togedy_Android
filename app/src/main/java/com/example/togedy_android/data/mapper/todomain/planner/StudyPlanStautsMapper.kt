package com.example.togedy_android.data.mapper.todomain.planner

import com.example.togedy_android.data.remote.model.planner.resposne.StudyPlanStatusResponseDto
import com.example.togedy_android.domain.model.planner.StudyPlanStatus

fun StudyPlanStatusResponseDto.toDomain(): StudyPlanStatus = StudyPlanStatus(
    id = this.id,
    status = this.status
)