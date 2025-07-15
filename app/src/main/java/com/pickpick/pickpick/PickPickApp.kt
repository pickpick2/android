package com.pickpick.pickpick

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class PickPickApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
