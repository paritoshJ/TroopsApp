package com.social.troops

import android.app.Application
import com.google.firebase.FirebaseApp
import com.social.troops.newWork.AppManager

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppManager.setInstance(AppManager(applicationContext));
        FirebaseApp.initializeApp(applicationContext)
    }
}