package com.example.togedy_android.domain.model.calendar

data class DailyScheduleItem(
    val id: Int,
    val name: String,
    val memo: String,
    val startDate: String,
    val endDate: String? = null,
    val category: List<CategoryItem>,
)
