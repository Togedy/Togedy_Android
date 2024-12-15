package com.example.togedy_android.data.mapper.todomain.planner

import com.example.togedy_android.data.remote.model.planner.resposne.StudyTagIdResponseDto
import com.example.togedy_android.domain.model.planner.StudyTagId

fun StudyTagIdResponseDto.toDomain(): StudyTagId= StudyTagId(
    studyTagId = this.studyTagId
)