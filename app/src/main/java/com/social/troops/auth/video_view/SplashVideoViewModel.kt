package com.social.troops.auth.video_view

import android.text.TextUtils
import com.social.troops.R
import com.social.troops.AppManager
import com.social.troops.domain.base.BaseFragment
import com.social.troops.domain.base.BaseViewModel
import com.social.troops.domain.repos.AuthRepo
import com.social.troops.domain.utils.Utils
import kotlinx.coroutines.launch

class SplashVideoViewModel : BaseViewModel() {
    lateinit var viewActions: AuthMainFragment.ViewActions


    fun navigateToRegister() {
        viewActions.navigateToRegister()
    }

    fun navigateToLogin() {
        viewActions.navigateToLogin()
    }

}
