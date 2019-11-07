package com.social.troops.newWork.auth.reset_password

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.social.troops.R
import com.social.troops.databinding.FragmentResetPasswordBinding
import com.social.troops.newWork.auth.base.AuthBaseFragment

class ResetPasswordFragment : AuthBaseFragment<ResetPasswordViewModel, FragmentResetPasswordBinding>() {

    var email: String? = null

    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getViewModelClass(): Class<ResetPasswordViewModel> {
        return ResetPasswordViewModel::class.java
    }

    override fun getToolbarMenuHandler(): ToolbarMenuHandler? {
        return null
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_reset_password
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewActions = object : ViewActions {
            override fun navigateBack() {
                getFragmentListener()?.navigateToLoginFragment()
            }

            override fun onResetPasswordSucceed(message: String) {
                showToast(message)
                getFragmentListener()?.navigateToLoginFragment()
            }
        }
        email?.let {
            viewBinding.editEmail.setText(email)
        }

        viewBinding.editConfirmPassword.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_GO) {

                getEdtData()
                viewModel.onSubmitClicked()
                true
            }
            false
        })


        viewBinding.imageBack.setOnClickListener { viewModel.onBackClicked() }
        viewBinding.buttonSubmit.setOnClickListener {
            getEdtData()
            viewModel.onSubmitClicked()
        }


    }

    fun getEdtData(){
        viewModel.email=viewBinding.editEmail.text.toString()
        viewModel.password=viewBinding.editPassword.text.toString()
        viewModel.confirmPassword=viewBinding.editConfirmPassword.text.toString()
    }

    override fun showInputError(inputError: InputError) {
        when (inputError.errorType) {
            InputErrorType.EMAIL -> {
                viewBinding.editEmail.requestFocus()
                showToast(inputError.message)
            }
            InputErrorType.PASSWORD -> {
                viewBinding.editPassword.requestFocus()
                showToast(inputError.message)
            }
            InputErrorType.CONFIRM_PASSWORD -> {
                viewBinding.editConfirmPassword.requestFocus()
                showToast(inputError.message)
            }
            InputErrorType.MISMATCH_PASSWORD -> {
                viewBinding.editConfirmPassword.requestFocus()
                showToast(inputError.message)
            }

        }
    }

    companion object {
        val TAG = "reset09"
        val ARG_EMAIL = "4fc24"

        fun newInstance(email: String): ResetPasswordFragment {
            val bundle = Bundle()
            bundle.putString(ARG_EMAIL, email)
            val frag = ResetPasswordFragment()
            frag.arguments = bundle
            return frag
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey(ARG_EMAIL)) email = it.getString(ARG_EMAIL)
        }
    }

    interface ViewActions {
        fun navigateBack()
        fun onResetPasswordSucceed(message: String)
    }
}