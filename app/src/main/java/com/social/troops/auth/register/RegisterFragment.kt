package com.social.troops.newWork.auth.register

import android.os.Bundle
import android.view.View
import com.social.troops.R
import com.social.troops.databinding.FragmentRegisterBinding
import com.social.troops.newWork.auth.base.AuthBaseFragment

class RegisterFragment : AuthBaseFragment<RegisterViewModel, FragmentRegisterBinding>() {

    var showBack = true

    override fun getViewModelClass(): Class<RegisterViewModel> {
        return RegisterViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return 0
    }

    companion object {
        val TAG = "reg09"
        val ARG_SHOW_BACK = "show0dsback"
        fun newInstance() = RegisterFragment()

        fun newInstance(showBack: Boolean): RegisterFragment {
            val bundle = Bundle()
            bundle.putBoolean(ARG_SHOW_BACK, showBack)
            val frag = RegisterFragment()
            frag.arguments = bundle
            return frag
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey(ARG_SHOW_BACK)) showBack = it.getBoolean(ARG_SHOW_BACK)
        }
    }

    override fun getToolbarMenuHandler(): ToolbarMenuHandler? {
        return null
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_register
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewActions = object : ViewActions {
            override fun onRegistrationSuccessful( message: String) {
                showToast(message)
                getFragmentListener()?.navigateToLoginFragment()
                /*getFragmentListener()?.navigateToVerifyRegistrationOtpFragment(
                    viewModel.email,
                    ("+".plus(viewBinding.pickerCountryCode.selectedCountryCode).plus(viewModel.mobileNumber)), user
                )*/
            }

            override fun getCountryCode(): String {
                return ""
            }

            override fun navigateToLogin() {
//                getFragmentListener()?.navigateToLoginFragment()
                activity?.onBackPressed()
            }
        }

        if (showBack) {
//            viewBinding.imageBack.visibility = View.VISIBLE
//            viewBinding.constBack.isClickable = true
        }



        viewBinding.signUpBtn.setOnClickListener {
            viewModel.firstName=viewBinding.edtFullName.text.toString()
            viewModel.email=viewBinding.editEmail.text.toString()
            viewModel.mobileNumber=viewBinding.editPhone.text.toString()
            viewModel.password=viewBinding.editPassword.text.toString()

            viewModel.onRegisterClicked() }

    }

    override fun showInputError(inputError: InputError) {
        when (inputError.errorType) {
            InputErrorType.FIRST_NAME -> {
                viewBinding.edtFullName.requestFocus()
                showToast(inputError.message)
            }

            InputErrorType.EMAIL -> {
                viewBinding.editEmail.requestFocus()
                showToast(inputError.message)
            }
            InputErrorType.MOBILE -> {
                viewBinding.editPhone.requestFocus()
                showToast(inputError.message)
            }
            InputErrorType.PASSWORD -> {
                viewBinding.editPassword.requestFocus()
                showToast(inputError.message)
            }


        }
    }

    interface ViewActions {
        fun navigateToLogin()
        fun onRegistrationSuccessful( message: String)
        fun getCountryCode(): String
    }
}