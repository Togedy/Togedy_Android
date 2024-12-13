package com.example.togedy_android.presentation.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UnivListViewModel @Inject constructor(

): ViewModel() {
    private val allUniversities = listOf(
        "경희대학교", "고려대학교", "가톨릭대학교",
        "나사렛대학교", "남서울대학교",
        "단국대학교", "덕성여자대학교",
        "서울대학교", "성균관대학교", "숙명여자대학교",
        "연세대학교", "이화여자대학교",
        "중앙대학교", "전남대학교",
        "충북대학교", "청주대학교",
        "한국외국어대학교", "한양대학교",
        "홍익대학교"
    )
    private val savedUniversities = listOf("고려대학교", "연세대학교", "한양대학교")

    private val _univGroups = MutableStateFlow<Map<Char, List<String>>>(emptyMap())
    val univGroups: StateFlow<Map<Char, List<String>>> = _univGroups

    private val _savedUnivGroups = MutableStateFlow<Map<Char, List<String>>>(emptyMap())
    val savedUnivGroups: StateFlow<Map<Char, List<String>>> = _savedUnivGroups

    init {
        viewModelScope.launch {
            _univGroups.value = groupUniversitiesByInitial(allUniversities)
            _savedUnivGroups.value = groupUniversitiesByInitial(savedUniversities)
        }
    }

    private fun groupUniversitiesByInitial(universities: List<String>): Map<Char, List<String>> {
        val initials = listOf(
            'ㄱ', 'ㄴ', 'ㄷ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅅ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
        )

        return universities
            .groupBy { university ->
                val char = university.first()
                if (char in '가'..'힣') {
                    val unicode = char.code - 0xAC00
                    val initialIndex = unicode / (21 * 28)
                    initials.getOrNull(initialIndex) ?: '?'
                } else {
                    '?'
                }
            }
            .filterKeys { it in initials } // 초성 범위 외 값 필터링
            .toSortedMap()
    }
}