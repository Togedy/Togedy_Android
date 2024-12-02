package com.example.togedy_android.presentation.community

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WritingViewModel : ViewModel() {
    private val _title = MutableStateFlow<String>("")
    val title: StateFlow<String> get() = _title
    private val _showPhotoError = MutableStateFlow<Boolean>(false)
    val showPhotoError: StateFlow<Boolean> get() = _showPhotoError
    private val _content = MutableStateFlow<String>("")
    val content: StateFlow<String> get() = _content
    private val _isActivated = MutableStateFlow<Boolean>(false)
    val isActivated: StateFlow<Boolean> get() = _isActivated
    private val _photoItems = mutableStateListOf<Int>()
    val photoItems: List<Int> get() = _photoItems

    fun updateTitle(newTitle: String) {
        _title.value = newTitle
    }

    fun updateShowPhotoError(result: Boolean) {
        _showPhotoError.value = result
    }

    fun updateContent(newContent: String) {
        _content.value = newContent
    }

    fun updateIsActivated() {
        _isActivated.value = _title.value.isNotBlank() && _content.value.isNotBlank()
    }

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