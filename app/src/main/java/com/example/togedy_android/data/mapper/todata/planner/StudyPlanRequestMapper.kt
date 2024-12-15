package com.example.togedy_android.data.mapper.todata.planner

import com.example.togedy_android.data.remote.model.planner.request.StudyPlanRequestDto
import com.example.togedy_android.domain.model.planner.NewStudyPlan

fun NewStudyPlan.toData(): StudyPlanRequestDto = StudyPlanRequestDto(
    name = this.name,
    date = this.date,
    studyTagId = this.studyTagId,
)