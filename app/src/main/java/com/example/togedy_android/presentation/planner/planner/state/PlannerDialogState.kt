package com.example.togedy_android.presentation.planner.planner.state

import com.example.togedy_android.domain.model.planner.PlanItem
import com.example.togedy_android.domain.model.planner.StudyTagItem

data class PlannerDialogState(
    val isAddSubjectDialogVisible: Boolean = false,
    val isEditSubjectDialogVisible: Boolean = false,
    val isAddPlanDialogVisible: Boolean = false,
    val isEditPlanDialogVisible: Boolean = false,
    val isEditPlanStateDialogVisible: Boolean = false,
    val studyTagItemInfo: StudyTagItem = StudyTagItem(id = -1, name = "", color = ""),
    val planInfo: PlanItem = PlanItem(todoID = -1, title = "", subjectColor = "", status = "")
)
