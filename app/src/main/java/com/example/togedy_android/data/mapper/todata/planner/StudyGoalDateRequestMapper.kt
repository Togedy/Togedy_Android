package com.example.togedy_android.data.mapper.todata.planner

import com.example.togedy_android.data.remote.model.request.StudyGoalDateRequestDto
import com.example.togedy_android.domain.model.planner.StudyGoalDate

fun StudyGoalDate.toData() : StudyGoalDateRequestDto = StudyGoalDateRequestDto(
    date = this.date
)