package com.example.togedy_android.data.local

import android.content.Context

fun saveAccessToken(context: Context, token: String) {
    val preferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    val editor = preferences.edit()
    editor.putString("access_token", "Bearer $token")
    editor.apply()
}

fun saveKakaoToken(context: Context, token: String) {
    val preferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    val editor = preferences.edit()
    editor.putString("kakao_token", token)
    editor.apply()
}

fun getKakaoToken(context: Context): String? {
    val preferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    return preferences.getString("kakao_token", null)
}


fun getAccessToken(context: Context): String? {
    val preferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    return preferences.getString("access_token", null)
}