package com.social.troops.auth.base.forgot_password

import android.os.Bundle
import android.view.View
import com.social.troops.R
import com.social.troops.databinding.FragmentForgotPasswordBinding
import com.social.troops.auth.base.AuthBaseFragment
import com.social.troops.auth.forgot_password.ForgotPasswordViewModel
import com.social.troops.data.RepoListener

class ForgotPasswordFragment : AuthBaseFragment<ForgotPasswordViewModel, FragmentForgotPasswordBinding>(),
        RepoListener {

    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getViewModelClass(): Class<ForgotPasswordViewModel> {
        return ForgotPasswordViewModel::class.java
    }

    override fun getToolbarMenuHandler(): ToolbarMenuHandler? {
        return null
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_forgot_password
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewActions = object : ViewActions {
            override fun navigateBack() {
                activity?.onBackPressed()
            }

            override fun navigateToResetPassword(message: String) {
                getFragmentListener()?.navigateToVerifyForgotPasswordOtpFragment(viewModel.email)
            }
        }


//        viewBinding.textDontHave.setOnClickListener { viewModel.navigateToLogin() }
        viewBinding.signInBtn.setOnClickListener {

            viewModel.email=viewBinding.emailId.text.toString()
            viewModel.onResetPasswordClicked() }
//        viewBinding.imageBack.setOnClickListener { viewModel.onBackClicked() }

    }

    override fun showInputError(inputError: InputError) {
        when (inputError.errorType) {
            InputErrorType.EMAIL -> {
                viewBinding.emailId.requestFocus()
                showToast(inputError.message)
            }
        }
    }

    companion object {
        val TAG = "f0urGoat"
        fun newInstance() = ForgotPasswordFragment()
    }

    interface ViewActions {
        fun navigateBack()
        fun navigateToResetPassword(message: String)
    }
}