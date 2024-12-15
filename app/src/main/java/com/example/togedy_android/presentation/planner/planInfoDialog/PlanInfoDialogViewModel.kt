package com.example.togedy_android.presentation.planner.planInfoDialog

import androidx.lifecycle.ViewModel
import com.example.togedy_android.domain.model.planner.StudyTagItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PlanInfoDialogViewModel @Inject constructor(
    //repository
) : ViewModel() {
    private val _studyTagItemList = MutableStateFlow(ArrayList<StudyTagItem>())
    val studyTagList = _studyTagItemList.asStateFlow()

    fun getStudyTagList() {
        _studyTagItemList.value = arrayListOf(
            StudyTagItem(id = 3, name = "국어", color = "color4"),
            StudyTagItem(id = 4, name = "수학", color = "color7"),
            StudyTagItem(id = 5, name = "국어", color = "color14"),
            StudyTagItem(id = 6, name = "수학", color = "color11"),
            StudyTagItem(id = 7, name = "국어", color = "color10"),
            StudyTagItem(id = 8, name = "수학", color = "color9"),
        )
    }
}