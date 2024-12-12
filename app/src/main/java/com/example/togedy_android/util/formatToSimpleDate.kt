package com.example.togedy_android.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun String.formatToSimpleDate(): String {
    if (this.isBlank()) {
        // 기본값 반환인 경우 빈 값
        return ""
    }

    return try {
        // 소수점 부분 조정
        val adjustedDateTime = if (this.contains(".")) {
            this.substringBefore(".")
        } else {
            this
        }

        // 날짜 파싱
        val dateTime = LocalDateTime.parse(adjustedDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME)

        // 원하는 포맷으로 변환
        val desiredFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        dateTime.format(desiredFormat)
    } catch (e: Exception) {
        e.printStackTrace()
        "Invalid Date"
    }
}
