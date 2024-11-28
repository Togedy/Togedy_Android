package com.example.togedy_android.presentation.ui.screens.calendar.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CalendarCategoryViewModel :  ViewModel() {

    private val _categoryItems = mutableListOf<String>()
    val categoryItems: List<String> get() = _categoryItems

    fun loadCategoryItems() {
        viewModelScope.launch {
            _categoryItems.addAll(
                listOf(
                    "건대",
                    "건국대",
                    "건국대학교",
                    "입시설명회",
                    "건대",
                    "건국대",
                    "건국대학교",
                    "입시설명회"
                )
            )
        }
    }
}