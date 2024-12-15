package com.example.togedy_android.data.mapper.todomain.planner

import com.example.togedy_android.data.remote.model.planner.resposne.StudyTagItemResponseDto
import com.example.togedy_android.domain.model.planner.StudyTagItem

fun List<StudyTagItemResponseDto>.toDomain(): List<StudyTagItem> {
    return this.map { studyTagItem ->
        StudyTagItem(
            id = studyTagItem.id,
            name = studyTagItem.name,
            color = studyTagItem.color
        )
    }
}