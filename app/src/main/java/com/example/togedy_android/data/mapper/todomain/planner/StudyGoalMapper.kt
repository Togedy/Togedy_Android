package com.example.togedy_android.data.mapper.todomain.planner

import com.example.togedy_android.data.remote.model.planner.resposne.StudyGoalResponseDto
import com.example.togedy_android.domain.model.planner.StudyGoal

fun StudyGoalResponseDto.toDomain(): StudyGoal = StudyGoal(
    id = this.id,
    targetTime = this.targetTime,
    actualTime = this.actualTime,
    achievement = this.achievement
)