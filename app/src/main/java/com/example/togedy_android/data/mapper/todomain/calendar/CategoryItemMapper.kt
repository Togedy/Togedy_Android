package com.example.togedy_android.data.mapper.todomain.calendar

import com.example.togedy_android.data.remote.model.calendar.response.CategoryItemResponseDto
import com.example.togedy_android.domain.model.calendar.CategoryItem

fun CategoryItemResponseDto.toDomain(): CategoryItem = CategoryItem(
    id = this.id,
    name = this.name,
    color = this.color
)
