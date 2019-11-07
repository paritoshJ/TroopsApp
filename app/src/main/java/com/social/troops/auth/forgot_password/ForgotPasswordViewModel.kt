package com.social.troops.newWork.auth.forgot_password

import android.text.TextUtils
import com.social.troops.R
import com.social.troops.newWork.AppManager
import com.social.troops.newWork.auth.base.forgot_password.ForgotPasswordFragment
import com.social.troops.newWork.domain.base.BaseFragment
import com.social.troops.newWork.domain.base.BaseViewModel
import com.social.troops.newWork.domain.repos.AuthRepo
import com.social.troops.newWork.domain.utils.Utils
import kotlinx.coroutines.launch

class ForgotPasswordViewModel : BaseViewModel() {

//    val navigateBack = MutableLiveData<Boolean>()
//    val navigateToResetPassword = MutableLiveData<String>()
    lateinit var viewActions: ForgotPasswordFragment.ViewActions
    var email: String = ""

    fun requestForgotPassword(email: String) {
        uiScope.launch {
            val res = AuthRepo(repoListener).requestForgotPassword(email)
            res?.apply {
                if (this.status) {
                    viewActions.navigateToResetPassword(this.message)
                } else showToast.value = this.message
            }
        }
    }

    fun onBackClicked() {
        viewActions.navigateBack()
    }

    fun navigateToLogin() {
        viewActions.navigateBack()
    }

    fun onResetPasswordClicked() {
        closeKeyBoard.value = true
        if (TextUtils.isEmpty(email)) {
            showInputError.value = BaseFragment.InputError(
                    BaseFragment.InputErrorType.EMAIL,
                    AppManager.getString(R.string.err_email_empty)
            )
            return
        }
        if (!Utils.isValidEmail(email)) {
            showInputError.value = BaseFragment.InputError(
                    BaseFragment.InputErrorType.EMAIL,
                    AppManager.getString(R.string.err_email_invalid)
            )
            return
        }
        requestForgotPassword(email)
    }
}
