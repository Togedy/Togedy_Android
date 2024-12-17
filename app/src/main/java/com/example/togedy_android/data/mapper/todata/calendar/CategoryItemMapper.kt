package com.example.togedy_android.data.mapper.todata.calendar

import com.example.togedy_android.data.remote.model.calendar.request.CategoryItemRequestDto
import com.example.togedy_android.domain.model.calendar.NewCategoryItem

fun NewCategoryItem.toData(): CategoryItemRequestDto = CategoryItemRequestDto(
    name = this.name,
    color = this.color
)