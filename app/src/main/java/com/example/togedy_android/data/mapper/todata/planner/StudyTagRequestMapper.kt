package com.example.togedy_android.data.mapper.todata.planner

import com.example.togedy_android.data.remote.model.planner.request.StudyTagItemRequestDto
import com.example.togedy_android.domain.model.planner.NewStudyTageItem

fun NewStudyTageItem.toData(): StudyTagItemRequestDto = StudyTagItemRequestDto(
    name = this.name,
    color = this.color
)