package com.example.togedy_android.domain.entity

import com.example.togedy_android.domain.model.planner.PlanItem
import com.example.togedy_android.domain.model.planner.StudyGoal
import com.example.togedy_android.domain.model.planner.StudyTagItem

data class PlannerHomeInformation(
    val studyGoal: StudyGoal,
    val planList: List<PlanItem> = emptyList<PlanItem>(),
    val studyTagItemLists: List<StudyTagItem> = emptyList<StudyTagItem>()
)