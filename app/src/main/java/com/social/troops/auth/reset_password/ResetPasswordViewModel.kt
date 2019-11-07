package com.social.troops.newWork.auth.reset_password

import android.text.TextUtils
import com.social.troops.R
import com.social.troops.newWork.AppManager
import com.social.troops.newWork.domain.base.BaseFragment
import com.social.troops.newWork.domain.base.BaseViewModel
import com.social.troops.newWork.domain.repos.AuthRepo
import com.social.troops.newWork.domain.utils.Utils
import kotlinx.coroutines.launch

class ResetPasswordViewModel : BaseViewModel() {

    lateinit var viewActions: ResetPasswordFragment.ViewActions

    var email: String = ""
    var password: String = ""
    var confirmPassword: String = ""

    fun requestResetPassword(email: String, password: String, confirmPassword: String, otp: String) {
        uiScope.launch {
            val res = AuthRepo(repoListener).requestResetPassword( password, confirmPassword, otp)
            res?.apply {
                if (this.status) {
                    viewActions.onResetPasswordSucceed(this.message)
                } else showToast.value = this.message
            }
        }
    }

    fun onBackClicked() {
        viewActions.navigateBack()
    }

    fun onSubmitClicked() {
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
        if (TextUtils.isEmpty(password)) {
            showInputError.value =
                    BaseFragment.InputError(
                            BaseFragment.InputErrorType.PASSWORD,
                            AppManager.getString(R.string.err_password_empty)
                    )
            return
        }

        if (password.trim().length<6) {
            showInputError.value =
                BaseFragment.InputError(
                    BaseFragment.InputErrorType.PASSWORD,
                    AppManager.getString(R.string.err_password_min_length)
                )
            return
        }


//        if (!Utils.isValidPassword(password.trim())) {
//            showInputError.value =
//                BaseFragment.InputError(
//                    BaseFragment.InputErrorType.PASSWORD,
//                    AppManager.getString(R.string.err_password_invalid)
//                )
//            return
//        }



        if (TextUtils.isEmpty(confirmPassword)) {
            showInputError.value =
                    BaseFragment.InputError(
                            BaseFragment.InputErrorType.CONFIRM_PASSWORD,
                            AppManager.getString(R.string.err_confirm_password_empty)
                    )
            return
        }
        if (!confirmPassword.equals(password)) {
            showInputError.value =
                    BaseFragment.InputError(
                            BaseFragment.InputErrorType.MISMATCH_PASSWORD,
                            AppManager.getString(R.string.err_mismatch_password)
                    )
            return
        }

        requestResetPassword(email, password, confirmPassword, "")
    }
}
