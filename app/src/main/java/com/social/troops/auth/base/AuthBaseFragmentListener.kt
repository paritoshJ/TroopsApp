package com.social.troops.auth.base

import com.social.troops.domain.models.User


interface AuthBaseFragmentListener {

    fun navigateToRegisterFragment()

    fun navigateToLoginFragment()

    fun navigateToForgotPasswordFragment()

    fun navigateToHomeActivity()

    fun navigateToRefralFragment(email: String)

    fun navigateToResetPasswordFragment(email: String)

    fun navigateToVerifyForgotPasswordOtpFragment(email: String)

    fun navigateToVerifyRegistrationOtpFragment(email: String, mobile: String, user: User)
}