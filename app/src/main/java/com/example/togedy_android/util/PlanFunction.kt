package com.example.togedy_android.util

import com.example.togedy_android.domain.type.PlanState

fun String.toPlanState(): PlanState {
    return PlanState.entries.find { it.state == this } ?: PlanState.NOT_STARTED
}