package com.example.togedy_android.data.local

import android.content.Context

fun saveAccessToken(context: Context, token: String) {
    val preferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    val editor = preferences.edit()
    editor.putString("access_token", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiZW1haWwiOiLtg5zsoJUiLCJleHAiOjE3Mzg5MTI4NTB9.e-qSBjl-05OkhXGOYq_H6b6sC2USOFXLmZ_INrW9bkc")
    editor.apply()
}

fun getAccessToken(context: Context): String? {
    val preferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    return preferences.getString("access_token", null)
}