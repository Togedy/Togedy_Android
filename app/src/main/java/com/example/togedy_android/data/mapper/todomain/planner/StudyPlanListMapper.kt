package com.example.togedy_android.data.mapper.todomain.planner

import com.example.togedy_android.data.remote.model.planner.resposne.StudyPlanResponseDto
import com.example.togedy_android.domain.model.planner.StudyPlanItem

fun List<StudyPlanResponseDto>.toDomain(): List<StudyPlanItem> {
    return this.map { studyPlanItem ->
        StudyPlanItem(
            studyPlanId = studyPlanItem.studyPlanId,
            name = studyPlanItem.name,
            studyTagColor = studyPlanItem.studyTagColor,
            studyTagId = studyPlanItem.studyTagId,
            planStatus = studyPlanItem.planStatus,
            studyRecords = studyPlanItem.studyRecords
        )
    }
}