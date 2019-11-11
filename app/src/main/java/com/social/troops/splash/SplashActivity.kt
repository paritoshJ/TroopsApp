package com.social.troops.splash

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.social.troops.AppManager
import com.social.troops.R
import com.social.troops.auth.AuthActivity
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {

    companion object {
        val TAG = "v254hb"

        fun createDispatcherIntent(context: Context): Intent {
            val intent = Intent(context, SplashActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
//        Utils.printKeyHash(this)
        Handler().postDelayed(Runnable {
            if (AppManager.isUserLoggedIn()) {
              //  startActivity(HomeActivity.createDispatcherIntent(this))
            } else
                startActivity(AuthActivity.createDispatcherIntent(this,AuthActivity.VIEW_TYPE_LOGIN, true))
        }, 1000)
    }




}
