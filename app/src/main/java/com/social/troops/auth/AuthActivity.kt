package com.social.troops.newWork.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import com.social.troops.R
import com.social.troops.databinding.ActivityAuthBinding
import com.social.troops.newWork.auth.base.AuthBaseFragmentListener
import com.social.troops.newWork.auth.base.forgot_password.ForgotPasswordFragment
import com.social.troops.newWork.auth.login.LoginFragment
import com.social.troops.newWork.auth.register.RegisterFragment
import com.social.troops.newWork.auth.reset_password.ResetPasswordFragment
import com.social.troops.newWork.auth.verify_otp.forgot_password.VerifyForgotPasswordOtpFragment
import com.social.troops.newWork.auth.verify_otp.register.VerifyRegistrationOtpFragment
import com.social.troops.newWork.domain.base.BaseActivity
import com.social.troops.newWork.domain.models.User

class AuthActivity : BaseActivity<AuthMainViewModel, ActivityAuthBinding>(), AuthBaseFragmentListener {


    override fun getViewModelClass(): Class<AuthMainViewModel> {
        return AuthMainViewModel::class.java
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_auth
    }

    override fun getBindingVariable(): Int {
        return 0
    }

    var loginFragment: LoginFragment? = null
    var viewType: Int = -1
    var shouldNavigateBack = false
    var shouldNavigateToLogin = true

    companion object {
        val TAG = "autha3"
        val ARG_VIEW_TYPE = "87ffg4e34"
        val ARG_SHOULD_NAV_BACK = "shNavBa"
        val VIEW_TYPE_LOGIN = 5
        val VIEW_TYPE_REGISTER = 6
        val ARG_SHOULD_NAV_LOGIN = "shnavlog"

        fun createDispatcherIntent(context: Context, viewType: Int, shouldNavigateBack: Boolean): Intent {
            val intent = Intent(context, AuthActivity::class.java)
            intent.putExtra(ARG_VIEW_TYPE, viewType)
            intent.putExtra(ARG_SHOULD_NAV_BACK, shouldNavigateBack)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            return intent
        }

        fun createDispatcherIntent(
            context: Context,
            viewType: Int,
            shouldNavigateBack: Boolean,
            shouldNavigateToLogin: Boolean
        ): Intent {
            val intent = Intent(context, AuthActivity::class.java)
            intent.putExtra(ARG_VIEW_TYPE, viewType)
            intent.putExtra(ARG_SHOULD_NAV_BACK, shouldNavigateBack)
            intent.putExtra(ARG_SHOULD_NAV_LOGIN, shouldNavigateToLogin)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent != null && intent.extras != null && intent.extras.containsKey(ARG_VIEW_TYPE))
            viewType = intent.extras.getInt(ARG_VIEW_TYPE)
        if (intent != null && intent.extras != null && intent.extras.containsKey(ARG_SHOULD_NAV_BACK))
            shouldNavigateBack = intent.extras.getBoolean(ARG_SHOULD_NAV_BACK)
        if (intent != null && intent.extras != null && intent.extras.containsKey(ARG_SHOULD_NAV_LOGIN))
            shouldNavigateToLogin = intent.extras.getBoolean(ARG_SHOULD_NAV_LOGIN)

        when (viewType) {
            VIEW_TYPE_LOGIN -> navigateToLoginFragment()
            VIEW_TYPE_REGISTER -> {
                if (shouldNavigateToLogin) navigateToLoginFragment()
                navigateToRegisterFragment()
            }
            else -> navigateToLoginFragment()
        }
    }

    override fun navigateToRegisterFragment() {
        if (shouldNavigateToLogin)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RegisterFragment.newInstance(), RegisterFragment.TAG)
                .addToBackStack(RegisterFragment.TAG).commit()
        else
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RegisterFragment.newInstance(), RegisterFragment.TAG)
                /*.addToBackStack(RegisterFragment.TAG)*/.commit()
    }

    override fun navigateToLoginFragment() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        if (shouldNavigateBack)
            loginFragment = LoginFragment.newInstance(shouldNavigateBack)
        else loginFragment = LoginFragment.newInstance()
        loginFragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, it, LoginFragment.TAG).commit()
        }
    }

    override fun navigateToForgotPasswordFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ForgotPasswordFragment.newInstance(), ForgotPasswordFragment.TAG)
            .addToBackStack(ForgotPasswordFragment.TAG).commit()
    }

    override fun navigateToResetPasswordFragment(email: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ResetPasswordFragment.newInstance(email), ResetPasswordFragment.TAG)
            .addToBackStack(ResetPasswordFragment.TAG).commit()
    }

    override fun navigateToHomeActivity() {
//        startActivity(HomeActivity.createDispatcherIntent(this))
//        finish()
    }

    override fun navigateToVerifyRegistrationOtpFragment(email: String, mobile: String, user: User) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                VerifyRegistrationOtpFragment.newInstance(email, mobile, user),
                VerifyRegistrationOtpFragment.TAG
            )
            .addToBackStack(VerifyRegistrationOtpFragment.TAG).commit()
    }


    override fun navigateToRefralFragment(email:String) {
    }


    override fun navigateToVerifyForgotPasswordOtpFragment(email: String) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                VerifyForgotPasswordOtpFragment.newInstance(email),
                VerifyForgotPasswordOtpFragment.TAG
            )
            .addToBackStack(VerifyForgotPasswordOtpFragment.TAG).commit()
    }

//    override fun onBackPressed() {
//        val currentFrag = supportFragmentManager.findFragmentById(R.id.container)
//        if (currentFrag is RegisterFragment){
//
//        }
//        super.onBackPressed()
//    }

    override fun onResume() {
        super.onResume()
        Log.e("test","onDetach")
    }

    override fun onStop() {
        super.onStop()
        Log.e("test","onStop")
    }
}