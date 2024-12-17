package com.example.togedy_android.domain.model.planner

data class PlannerHomeInformation(
    val studyGoal: StudyGoal,
    val studyPlanList: List<StudyPlanItem> = emptyList<StudyPlanItem>(),
    val studyTagItemLists: List<StudyTagItem> = emptyList<StudyTagItem>()
)