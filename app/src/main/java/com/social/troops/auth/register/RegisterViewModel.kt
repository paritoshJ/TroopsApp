package com.social.troops.newWork.auth.register

import android.text.TextUtils
import com.social.troops.R
import com.social.troops.newWork.AppManager
import com.social.troops.newWork.domain.base.BaseFragment
import com.social.troops.newWork.domain.base.BaseViewModel
import com.social.troops.newWork.domain.repos.AuthRepo
import com.social.troops.newWork.domain.utils.Utils
import kotlinx.coroutines.launch

class RegisterViewModel : BaseViewModel() {

    lateinit var viewActions: RegisterFragment.ViewActions
    var firstName: String = ""
    var lastName: String = ""
    var email: String = ""
    var mobileNumber: String = ""
    var password: String = ""
    var confirmPassword: String = ""


    fun requestRegistration(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        mobile: String

    ) {
        uiScope.launch {
            val res =
                AuthRepo(repoListener).requestUserRegistration(firstName, lastName, email, password, mobile)
            res?.apply {
                if (this.status) {
                    viewActions.onRegistrationSuccessful( this.message)
                } else showToast.value = this.message
            }
        }
    }

    fun onRegisterClicked() {
        closeKeyBoard.value = true
        if (TextUtils.isEmpty(firstName)) {
            showInputError.value = BaseFragment.InputError(
                BaseFragment.InputErrorType.FIRST_NAME,
                AppManager.getString(R.string.err_first_name_empty)
            )
            return
        }

        if (firstName.trim().length<2) {
            showInputError.value = BaseFragment.InputError(
                BaseFragment.InputErrorType.FIRST_NAME,
                AppManager.getString(R.string.err_first_name_length)
            )
            return
        }

        if (TextUtils.isEmpty(lastName)) {
            showInputError.value = BaseFragment.InputError(
                BaseFragment.InputErrorType.LAST_NAME,
                AppManager.getString(R.string.err_last_name_empty)
            )
            return
        }


        if (lastName.trim().length<2) {
            showInputError.value = BaseFragment.InputError(
                BaseFragment.InputErrorType.LAST_NAME,
                AppManager.getString(R.string.error_last_name_length)
            )
            return
        }

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
        if (TextUtils.isEmpty(mobileNumber)) {
            showInputError.value = BaseFragment.InputError(
                BaseFragment.InputErrorType.MOBILE,
                AppManager.getString(R.string.err_mobile_empty)
            )
            return
        }

        if (mobileNumber.trim().length<10  ){
            showInputError.value = BaseFragment.InputError(
                BaseFragment.InputErrorType.MOBILE,
                AppManager.getString(R.string.err_mobile_length_condition)
            )
            return
        }

        if ( mobileNumber.trim().length>16 ){
            showInputError.value = BaseFragment.InputError(
                BaseFragment.InputErrorType.MOBILE,
                AppManager.getString(R.string.err_mobile_length_condition)
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

//        val num = "+".plus(viewActions.getCountryCode()).plus(mobileNumber)
        requestRegistration(firstName, lastName, email, password, mobileNumber)
    }


    fun navigateToLogin() {
        viewActions.navigateToLogin()
    }

    fun onBackClicked() {
        viewActions.navigateToLogin()
    }
}

