package com.example.togedy_android.data.mapper.todomain.calendar

import com.example.togedy_android.data.remote.model.calendar.response.CategoryItemResponseDto
import com.example.togedy_android.domain.model.calendar.CategoryItem

fun List<CategoryItemResponseDto>.toDomain(): List<CategoryItem> {
    return this.map { categoryItemResponseDto->
        CategoryItem(
            id = categoryItemResponseDto.id,
            name = categoryItemResponseDto.name,
            color = categoryItemResponseDto.color
        )
    }
}
