package com.example.togedy_android.data.mapper.todomain.calendar

import com.example.togedy_android.data.remote.model.calendar.response.CategoryIdResponseDto
import com.example.togedy_android.domain.model.calendar.CategoryId

fun CategoryIdResponseDto.toDomain(): CategoryId = CategoryId(
    categoryId = this.categoryId
)