package com.example.togedy_android.presentation.planner.planInfoDialog

import androidx.lifecycle.ViewModel
import com.example.togedy_android.domain.model.planner.StudyTag
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PlanInfoDialogViewModel @Inject constructor(
    //repository
) : ViewModel() {
    private val _studyTagList = MutableStateFlow(ArrayList<StudyTag>())
    val studyTagList = _studyTagList.asStateFlow()

    fun getStudyTagList() {
        _studyTagList.value = arrayListOf(
            StudyTag(subjectId = 3, name = "국어", color = "color4"),
            StudyTag(subjectId = 4, name = "수학", color = "color7"),
            StudyTag(subjectId = 5, name = "국어", color = "color14"),
            StudyTag(subjectId = 6, name = "수학", color = "color11"),
            StudyTag(subjectId = 7, name = "국어", color = "color10"),
            StudyTag(subjectId = 8, name = "수학", color = "color9"),
        )
    }
}