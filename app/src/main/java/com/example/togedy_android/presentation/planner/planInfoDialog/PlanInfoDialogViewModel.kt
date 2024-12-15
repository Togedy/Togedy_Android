package com.example.togedy_android.presentation.planner.planInfoDialog

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togedy_android.domain.model.planner.StudyTagItem
import com.example.togedy_android.domain.repository.PlannerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanInfoDialogViewModel @Inject constructor(
    private val plannerRepository: PlannerRepository
) : ViewModel() {
    private val _studyTagItemList = MutableStateFlow(emptyList<StudyTagItem>())
    val studyTagList = _studyTagItemList.asStateFlow()

    fun getStudyTagList() {
        viewModelScope.launch{
            plannerRepository.getStudyTagList()
                .onSuccess { studyTagItemList ->
                    _studyTagItemList.value = studyTagItemList
                }
                .onFailure { throwable ->
                    Log.d("API-PlannerViewModel", "getStudyTagList: 실패")
                }
        }
    }
}