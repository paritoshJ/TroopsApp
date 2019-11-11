package com.social.troops

import android.app.Application
import com.social.troops.AppManager

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppManager.setInstance(AppManager(applicationContext));
    }
}