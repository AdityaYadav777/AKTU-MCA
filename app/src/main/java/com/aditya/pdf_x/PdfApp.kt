package com.aditya.pdf_x

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PdfApp:Application() {
    override fun onCreate() {
        super.onCreate()
    }
}