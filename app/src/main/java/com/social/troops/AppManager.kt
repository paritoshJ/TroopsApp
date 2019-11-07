package com.social.troops.newWork

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import androidx.annotation.StringRes
import com.google.gson.Gson
import com.social.troops.newWork.domain.models.User
import com.social.troops.newWork.domain.utils.Utils

class AppManager(context: Context) {

    init {
        if (instance == null)
            preferences = context.applicationContext.getSharedPreferences(PrefKeys.PREF_NAME, Context.MODE_PRIVATE)
        c = context
    }

    private interface PrefKeys {
        companion object {
            val PREF_NAME = "smarTPrefs"
            val PREF_FCM_ID = "fcmii"
            val PREF_DEVICE_ID = "dev_ice_iD"
            val PREF_USER = "Er_us:)"
        }
    }

    companion object {

        val IS_LOGGING_ENABLED = true
        val IS_USER_BUYER  = "buyer"
        val IS_USER_SELLER  = "seller"
                val REMOTE_DATA_BASE_URL = "http://webappdeveloment.projectstatus.in/public/api/"
                val IMAGE_BASE_PATH = "http://webappdeveloment.projectstatus.in/public/storage"
//                val IMAGE_BASE_PATH = "http://192.168.4.92/marketPlace/public/storage"
//        val REMOTE_DATA_BASE_URL = "http://192.168.4.92/MarketPlace/public/api/"
        val DATABASE_NAME = "iu_dsu"
        val authSalt = "Bearer "
        val DEVICE_ID = "deviceId"

        private var instance: AppManager? = null
        var preferences: SharedPreferences? = null
            private set
        //        private var mFcmRegId: String? = null
//        private var user: User? = null
        private lateinit var c: Context

        var offerCode = ""

        fun getInstance(): AppManager? {
            return instance
        }

        @Synchronized
        fun setInstance(instance: AppManager) {
            if (Companion.instance == null)
                Companion.instance = instance
        }

        fun getPreferences(context: Context): SharedPreferences? {
            if (preferences == null)
                preferences = context.applicationContext.getSharedPreferences(PrefKeys.PREF_NAME, Context.MODE_PRIVATE)
            return preferences
        }

        var fcmRegId: String? = null
            get() {
                if (fcmRegId == null)
                    fcmRegId = preferences!!.getString(PrefKeys.PREF_FCM_ID, null)
                return fcmRegId
            }
            set(fcmRegId) {
                preferences!!.edit().putString(PrefKeys.PREF_FCM_ID, fcmRegId).apply()
                field = fcmRegId
            }

        private var sUser: User? = null
        fun getUser(): User? {
            if (sUser == null && preferences == null) return null
            else if (sUser == null) {
                preferences?.apply {
                    if (this.contains(PrefKeys.PREF_USER)) {
                        try {
                            val s = this.getString(PrefKeys.PREF_USER, null)
                            sUser = Gson().fromJson<User>(s, User::class.java)
                        } catch (e: Exception) {
                            sUser = null
                        }
                    }
                }
                return sUser
            }
            return sUser
        }

        fun setUser(user: User?) {
            preferences?.apply {
                this.edit().putString(PrefKeys.PREF_USER, Gson().toJson(user)).apply()
            }
            sUser = user
        }

        fun getUserFullName(): String {
            var name = ""
            getUser()?.let {
                if (!TextUtils.isEmpty(it.firstName)) {
                    name = name.plus(it.firstName)
                    if (!TextUtils.isEmpty(it.lastName)) {
                        name = name.plus(" ").plus(it.lastName)
                    }
                } else if (!TextUtils.isEmpty(it.lastName)) {
                    name = name.plus(it.lastName)
                }
            }
            return name
        }

        fun isUserLoggedIn(): Boolean {
            //        return getUser() != null && getUser().getToken() != null;
            return getUser() != null
        }

        fun hasFcmRegId(): Boolean {
            return !TextUtils.isEmpty(fcmRegId)
        }

        val deviceID: String?
            get() = preferences!!.getString(PrefKeys.PREF_DEVICE_ID, null)

        fun putDeviceID(deviceId: String) {
            preferences!!.edit().putString(PrefKeys.PREF_DEVICE_ID, deviceId).apply()
        }

        val context: Context
            get() {
                return c
            };


        fun getString(@StringRes str: Int): String {
            return c.getString(str)
        }

        fun isNetworkConnectedAvailable(): Boolean {
            return Utils.isNetworkConnectedAvailable(c)
        }
    }
}