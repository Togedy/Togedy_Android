package com.example.togedy_android.data.mapper.todomain.calendar

import com.example.togedy_android.data.remote.model.calendar.response.DailyScheduleItemDto
import com.example.togedy_android.domain.model.calendar.DailyScheduleItem

fun List<DailyScheduleItemDto>.toDomain(): List<DailyScheduleItem> {
    return this.map { dailyScheduleItemDto ->
        DailyScheduleItem(
            id = dailyScheduleItemDto.id,
            name = dailyScheduleItemDto.name,
            memo = dailyScheduleItemDto.memo,
            startDate = dailyScheduleItemDto.startDate,
            endDate = dailyScheduleItemDto.endDate,
            category = dailyScheduleItemDto.category.toDomain(),
        )
    }
}