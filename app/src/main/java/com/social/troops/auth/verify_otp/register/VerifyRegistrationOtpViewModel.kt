package com.social.troops.auth.verify_otp.register

import android.text.TextUtils
import com.social.troops.R
import com.social.troops.AppManager
import com.social.troops.domain.base.BaseFragment
import com.social.troops.domain.base.BaseViewModel
import com.social.troops.domain.repos.AuthRepo
import kotlinx.coroutines.launch

class VerifyRegistrationOtpViewModel : BaseViewModel() {

    lateinit var viewActions: VerifyRegistrationOtpFragment.ViewActions
    var email: String = ""
    var mobile: String = ""
    var otpEmail1 = ""
    var otpEmail2 = ""
    var otpEmail3 = ""
    var otpEmail4 = ""
    var otpEmail5 = ""
    var otpEmail6 = ""
    var otpMobile1 = ""
    var otpMobile2 = ""
    var otpMobile3 = ""
    var otpMobile4 = ""
    var otpMobile5 = ""
    var otpMobile6 = ""


    fun requestVerifyOtp() {
        val otpEmail = otpEmail1.plus(otpEmail2).plus(otpEmail3).plus(otpEmail4).plus(otpEmail5).plus(otpEmail6)
        val otpMobile = otpMobile1.plus(otpMobile2).plus(otpMobile3).plus(otpMobile4).plus(otpMobile5).plus(otpMobile6)
        uiScope.launch {
            val res = AuthRepo(repoListener).requestVerifyRegistrationOtp(otpEmail, otpMobile)
            res?.apply {
                if (this.status) {
                    viewActions.onOtpVerified(message)
                } else showToast.value = this.message
            }
        }
    }

    fun resendCode() {
        uiScope.launch {
            val res = AuthRepo(repoListener).requestResendRegistrationOtp(email, mobile)
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

        if (TextUtils.isEmpty(otpMobile1)) {
            showInputError.value = BaseFragment.InputError(
                    BaseFragment.InputErrorType.OTP_MOBILE_1,
                    AppManager.getString(R.string.err_otp_empty)
            )
            return
        }
        if (TextUtils.isEmpty(otpMobile2)) {
            showInputError.value = BaseFragment.InputError(
                    BaseFragment.InputErrorType.OTP_MOBILE_2,
                    AppManager.getString(R.string.err_otp_empty)
            )
            return
        }
        if (TextUtils.isEmpty(otpMobile3)) {
            showInputError.value = BaseFragment.InputError(
                    BaseFragment.InputErrorType.OTP_MOBILE_3,
                    AppManager.getString(R.string.err_otp_empty)
            )
            return
        }
        if (TextUtils.isEmpty(otpMobile4)) {
            showInputError.value = BaseFragment.InputError(
                    BaseFragment.InputErrorType.OTP_MOBILE_4,
                    AppManager.getString(R.string.err_otp_empty)
            )
            return
        }
        if (TextUtils.isEmpty(otpMobile5)) {
            showInputError.value = BaseFragment.InputError(
                    BaseFragment.InputErrorType.OTP_MOBILE_5,
                    AppManager.getString(R.string.err_otp_empty)
            )
            return
        }
        if (TextUtils.isEmpty(otpMobile6)) {
            showInputError.value = BaseFragment.InputError(
                    BaseFragment.InputErrorType.OTP_MOBILE_6,
                    AppManager.getString(R.string.err_otp_empty)
            )
            return
        }

        requestVerifyOtp()
    }

    fun navigateToBack() {
        viewActions.navigateBack()
    }
}
