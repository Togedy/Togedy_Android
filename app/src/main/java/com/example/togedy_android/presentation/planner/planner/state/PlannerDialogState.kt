package com.example.togedy_android.presentation.planner.planner.state

import com.example.togedy_android.domain.model.planner.NewStudyPlan
import com.example.togedy_android.domain.model.planner.StudyTagItem
import java.time.LocalDate

data class PlannerDialogState(
    val isAddSubjectDialogVisible: Boolean = false,
    val isEditSubjectDialogVisible: Boolean = false,
    val isAddPlanDialogVisible: Boolean = false,
    val isEditPlanDialogVisible: Boolean = false,
    val isEditPlanStateDialogVisible: Boolean = false,
    val studyTagItemInfo: StudyTagItem = StudyTagItem(id = -1, name = "", color = ""),
    val planInfo: NewStudyPlan = NewStudyPlan(
        name = "",
        date = LocalDate.now().toString(),
        studyTagId = -1,
    ),
    val selectedDay: LocalDate = LocalDate.now()
)
