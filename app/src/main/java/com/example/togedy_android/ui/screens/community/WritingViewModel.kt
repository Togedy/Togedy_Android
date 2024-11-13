package com.example.togedy_android.ui.screens.community

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class WritingViewModel : ViewModel() {

    private val _photoItems = mutableStateListOf<Int>()
    val photoItems: List<Int> get() = _photoItems


    fun addPhotoItems(photo: Int): Boolean {
        return if (_photoItems.size < 3) {
            _photoItems.add(photo)
            true
        } else {
            false
        }
    }

    fun deletePhotoItems(index: Int) {
        if (index in _photoItems.indices) {
            _photoItems.removeAt(index)
        }
    }
}