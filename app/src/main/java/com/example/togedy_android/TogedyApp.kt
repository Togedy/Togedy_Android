package com.example.togedy_android

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TogedyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        setDartMode()
    }

    private fun setDartMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}