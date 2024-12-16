package com.example.togedy_android.data.mapper.todata

import com.example.togedy_android.data.remote.model.planner.DateRequestDto
import com.example.togedy_android.domain.model.DateModel

fun DateModel.toData() : DateRequestDto = DateRequestDto(
    date = this.date
)