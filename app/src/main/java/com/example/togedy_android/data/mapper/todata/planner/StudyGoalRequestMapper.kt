package com.example.togedy_android.data.mapper.todata.planner

import com.example.togedy_android.data.remote.model.request.StudyGoalRequestDto
import com.example.togedy_android.domain.model.planner.NewStudyGoal

fun NewStudyGoal.toData(): StudyGoalRequestDto = StudyGoalRequestDto(
    date = this.date,
    targetTime = this.targetTime,
)