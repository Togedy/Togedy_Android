package com.example.togedy_android.domain.entity

import com.example.togedy_android.domain.model.planner.StudyGoal
import com.example.togedy_android.domain.model.planner.StudyPlanItem
import com.example.togedy_android.domain.model.planner.StudyTagItem

data class PlannerHomeInformation(
    val studyGoal: StudyGoal,
    val studyPlanList: List<StudyPlanItem> = emptyList<StudyPlanItem>(),
    val studyTagItemLists: List<StudyTagItem> = emptyList<StudyTagItem>()
)