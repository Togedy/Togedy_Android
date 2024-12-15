package com.example.togedy_android.data.mapper.todata.planner

import com.example.togedy_android.data.remote.model.planner.DateRequestDto
import com.example.togedy_android.domain.model.planner.Date

fun Date.toData() : DateRequestDto = DateRequestDto(
    date = this.date
)