package com.social.troops.newWork.auth.verify_otp.forgot_password

import android.text.TextUtils
import com.social.troops.R
import com.social.troops.newWork.AppManager
import com.social.troops.newWork.domain.base.BaseFragment
import com.social.troops.newWork.domain.base.BaseViewModel
import com.social.troops.newWork.domain.repos.AuthRepo
import kotlinx.coroutines.launch

class VerifyForgotPasswordOtpViewModel : BaseViewModel() {

    lateinit var viewActions: VerifyForgotPasswordOtpFragment.ViewActions
    var email: String = ""
    var otpEmail1 = ""
    var otpEmail2 = ""
    var otpEmail3 = ""
    var otpEmail4 = ""
    var otpEmail5 = ""
    var otpEmail6 = ""
    var password = ""
    var confirmPassword = ""

    fun requestVerifyOtp() {
        val otpEmail = otpEmail1.plus(otpEmail2).plus(otpEmail3).plus(otpEmail4).plus(otpEmail5).plus(otpEmail6)
        uiScope.launch {
            val res = AuthRepo(repoListener).requestResetPassword(password,confirmPassword,otpEmail)
            res?.apply {
                if (this.status) {
                    viewActions.onOtpVerified(message)
                } else showToast.value = this.message
            }
        }
    }

    fun resendCode() {
        uiScope.launch {
            val res = AuthRepo(repoListener).requestResendForgotPasswordOtp(email)
            res?.apply {
                if (this.status) {
                    viewActions.onOtpResent(this.message)
                } else showToast.value = this.message
            }
        }
    }

    fun onContinueClicked() {
        closeKeyBoard.value = true
        if (TextUtils.isEmpty(otpEmail1)) {
            showInputError.value = BaseFragment.InputError(
                    BaseFragment.InputErrorType.OTP_EMAIL_1,
                    AppManager.getString(R.string.err_otp_empty)
            )
            return
        }
        if (TextUtils.isEmpty(otpEmail2)) {
            showInputError.value = BaseFragment.InputError(
                    BaseFragment.InputErrorType.OTP_EMAIL_2,
                    AppManager.getString(R.string.err_otp_empty)
            )
            return
        }
        if (TextUtils.isEmpty(otpEmail3)) {
            showInputError.value = BaseFragment.InputError(
                    BaseFragment.InputErrorType.OTP_EMAIL_3,
                    AppManager.getString(R.string.err_otp_empty)
            )
            return
        }
        if (TextUtils.isEmpty(otpEmail4)) {
            showInputError.value = BaseFragment.InputError(
                    BaseFragment.InputErrorType.OTP_EMAIL_4,
                    AppManager.getString(R.string.err_otp_empty)
            )
            return
        }
        if (TextUtils.isEmpty(otpEmail5)) {
            showInputError.value = BaseFragment.InputError(
                    BaseFragment.InputErrorType.OTP_EMAIL_5,
                    AppManager.getString(R.string.err_otp_empty)
            )
            return
        }
        if (TextUtils.isEmpty(otpEmail6)) {
            showInputError.value = BaseFragment.InputError(
                    BaseFragment.InputErrorType.OTP_EMAIL_6,
                    AppManager.getString(R.string.err_otp_empty)
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
        if (password?.length!! <= 5) {
            showInputError.value =
                BaseFragment.InputError(
                    BaseFragment.InputErrorType.PASSWORD,
                    AppManager.getString(R.string.err_password_min_length)
                )
            return
        }
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

        requestVerifyOtp()
    }

    fun navigateToBack() {
        viewActions.navigateBack()
    }
}
