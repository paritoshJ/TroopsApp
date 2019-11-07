package com.social.troops.newWork.auth.video_view

import android.text.TextUtils
import com.social.troops.R
import com.social.troops.newWork.AppManager
import com.social.troops.newWork.domain.base.BaseFragment
import com.social.troops.newWork.domain.base.BaseViewModel
import com.social.troops.newWork.domain.repos.AuthRepo
import com.social.troops.newWork.domain.utils.Utils
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
