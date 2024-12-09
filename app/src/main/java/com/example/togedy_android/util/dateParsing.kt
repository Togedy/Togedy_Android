package com.example.togedy_android.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun String.formatToSimpleDate(): String {
    // 원본 날짜를 파싱
    val originalFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    val dateTime = LocalDateTime.parse(this, originalFormat)

    // 원하는 포맷으로 변환
    val desiredFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    return dateTime.format(desiredFormat)
}
