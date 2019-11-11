package com.social.troops.auth.login

import android.text.TextUtils
import com.social.troops.R
import com.social.troops.AppManager
import com.social.troops.domain.base.BaseFragment
import com.social.troops.domain.base.BaseViewModel
import com.social.troops.domain.repos.AuthRepo
import com.social.troops.domain.utils.Utils
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {

    lateinit var viewActions: LoginFragment.ViewActions

    var email: String = ""
    var password: String = ""
    var userid: String = ""
    var roleid: String = ""


//    fun requestLogin(email: String, password: String) {
//        uiScope.launch {
//
//        }
//    }

    fun getUiScop() = uiScope

    fun requestLogin(email: String, name: String) {
        uiScope.launch {
            val res = AuthRepo(repoListener).requestUserLogin(email, name,"","")
            res?.apply {
                if (this.status) {
                    AppManager.setUser(this.data)
                    viewActions.onLoginSuccessful(this.message)
                } else showToast.value = this.message
            }
        }
    }

    fun requestRoleSelection(userid:String,roleid: String) {
        uiScope.launch {
            val res = AuthRepo(repoListener).requestUpdateUserRole(userid, roleid)
            res?.apply {
                if (this.status) {
                    AppManager.setUser(this.data)
//                    AppManager.preferences?.edit()?.putString("auth_token",null)?.apply()
                    viewActions.onRoleSetSuccessful(this.message)
                } else showToast.value = this.message
            }
        }
    }


    fun navigateToRegister() {
        viewActions.navigateToRegister()
    }
    fun navigateToForgotPassword() {
        viewActions.navigateToForgotPassword()
    }

    fun onFacebookLoginClicked() {
        viewActions.onFacebookLoginClicked()
    }

    fun onGoogleLoginClicked() {
        viewActions.onGoogleLoginClicked()
    }

    fun onBackClicked() {
        viewActions.onBackClicked()
    }
    fun onRoleClicked() {
        requestRoleSelection(userid,roleid)
    }



    fun onLoginClicked() : Boolean{
        closeKeyBoard.value = true
        if (TextUtils.isEmpty(email)) {
            showInputError.value = BaseFragment.InputError(
                    BaseFragment.InputErrorType.EMAIL,
                    AppManager.getString(R.string.err_email_empty)
            )
            return true
        }
        if (!Utils.isValidEmail(email)) {
            showInputError.value = BaseFragment.InputError(
                    BaseFragment.InputErrorType.EMAIL,
                    AppManager.getString(R.string.err_email_invalid)
            )
            return true
        }
        if (TextUtils.isEmpty(password)) {
            showInputError.value =
                    BaseFragment.InputError(
                            BaseFragment.InputErrorType.PASSWORD,
                            AppManager.getString(R.string.err_password_empty)
                    )
            return true
        }
        if (password?.length!! <= 5) {
            showInputError.value =
                    BaseFragment.InputError(
                            BaseFragment.InputErrorType.PASSWORD,
                            AppManager.getString(R.string.err_password_min_length)
                    )
            return true
        }
      //  requestLogin(email, password)
        return false
    }
}
