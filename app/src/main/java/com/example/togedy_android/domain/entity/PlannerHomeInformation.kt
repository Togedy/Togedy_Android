package com.example.togedy_android.domain.entity

import com.example.togedy_android.domain.model.planner.DayOfPlan
import com.example.togedy_android.domain.model.planner.StudyTag


data class PlannerHomeInformation(
//    val todaysGoal: TodaysGoal,
    val todaysPlan: DayOfPlan = DayOfPlan(emptyList(), emptyList()),
    val studyTagList: List<StudyTag> = emptyList<StudyTag>()
)
