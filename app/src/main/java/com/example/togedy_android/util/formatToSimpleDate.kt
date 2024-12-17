package com.example.togedy_android.util

import java.time.LocalDate
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

// "2024-12-20" → "2024.12.20"
fun String.formatToDotSeparatedDate(): String {
    if (this.isBlank()) return ""

    return try {
        val date = LocalDate.parse(this, DateTimeFormatter.ISO_LOCAL_DATE)
        date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
    } catch (e: Exception) {
        e.printStackTrace()
        "Invalid Date"
    }
}

// "2024-12-20" → "2024년 12월 20일"
fun String.formatToKoreanDate(): String {
    if (this.isBlank()) return ""

    return try {
        val date = LocalDate.parse(this, DateTimeFormatter.ISO_LOCAL_DATE)
        date.format(DateTimeFormatter.ofPattern("yyyy년 M월 d일"))
    } catch (e: Exception) {
        e.printStackTrace()
        "Invalid Date"
    }
}

fun String.toHHhMmm(): String? {
    val regex = Regex("^\\d{2}:\\d{2}:\\d{2}$")
    if (!regex.matches(this)) return null

    val (hours, minutes, _) = this.split(":")
    return "${hours}h ${minutes}m"
}

fun String.toHHhMmmSss(): String? {
    val regex = Regex("^\\d{2}:\\d{2}:\\d{2}$")
    if (!regex.matches(this)) return null

    val (hours, minutes, seconds) = this.split(":")
    return "${hours}h ${minutes}m ${seconds}s"
}

fun String.toServerDataTime(): String? {
    val regex = Regex("^\\d{2}:\\d{2}:\\d{2}$")
    if (!regex.matches(this)) return null

    val (hours, minutes, _) = this.split(":")
    return "${hours}:${minutes}"
}