package com.example.togedy_android.domain.type

enum class PlanState(val state: String) {
    NOT_STARTED("NOT_STARTED"),
    COMPLETED("COMPLETED"),
    INCOMPLETE("INCOMPLETE"),
    NOT_EXECUTED("NOT_EXECUTED"),
    POSTPONED("POSTPONED"),
}