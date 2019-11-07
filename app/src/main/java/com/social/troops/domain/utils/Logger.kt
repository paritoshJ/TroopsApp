package com.app.marketplace.sellBuyDevice.domain.utils

import android.util.Log
import com.app.marketplace.sellBuyDevice.AppManager

class Logger {

    companion object {

        fun d(s: String) {
            if (AppManager.IS_LOGGING_ENABLED) Log.d("", s)
        }

        fun d(tag: String, s: String) {
            if (AppManager.IS_LOGGING_ENABLED) Log.d(tag, s)
        }

//        fun print(s: String) {
//            if (AppManager.IS_LOGGING_ENABLED) println(s)
//        }

        @JvmStatic
        fun print(s: String) {
            if (AppManager.IS_LOGGING_ENABLED) println(s)
        }
    }
}