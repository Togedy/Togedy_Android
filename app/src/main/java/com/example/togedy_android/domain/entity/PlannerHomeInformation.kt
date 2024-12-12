package com.example.togedy_android.domain.entity

import com.example.togedy_android.domain.model.planner.DayOfPlan
import com.example.togedy_android.domain.model.planner.PlanItem
import com.example.togedy_android.domain.model.planner.StudyTag


data class PlannerHomeInformation(
//    val todaysGoal: TodaysGoal,
    val planList: List<PlanItem> = emptyList<PlanItem>(),
    val studyTagList: List<StudyTag> = emptyList<StudyTag>()
)
