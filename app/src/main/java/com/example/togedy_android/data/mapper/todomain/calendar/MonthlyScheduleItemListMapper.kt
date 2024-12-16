package com.example.togedy_android.data.mapper.todomain.calendar

import com.example.togedy_android.data.remote.model.calendar.response.MonthlyScheduleItemDto
import com.example.togedy_android.domain.model.calendar.MonthlyScheduleItem

fun List<MonthlyScheduleItemDto>.toDomain(): List<MonthlyScheduleItem> {
    return this.map { monthlyScheduleItemDto ->
        MonthlyScheduleItem(
            date = monthlyScheduleItemDto.date,
            scheduleName = monthlyScheduleItemDto.scheduleName,
            categoryColor = monthlyScheduleItemDto.categoryColor
        )
    }
}