package com.example.iainteracitvemovies

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by Joao Betancourth on 22,enero,2022
 */

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}