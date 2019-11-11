package com.social.troops.auth.verify_otp.forgot_password

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.social.troops.R
import com.social.troops.databinding.FragmentVerifyOtpForgotPasswordBinding
import com.social.troops.auth.base.AuthBaseFragment
import com.social.troops.data.RepoListener


class VerifyForgotPasswordOtpFragment : AuthBaseFragment<VerifyForgotPasswordOtpViewModel, FragmentVerifyOtpForgotPasswordBinding>(),
        RepoListener {

    var email: String? = null

    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getViewModelClass(): Class<VerifyForgotPasswordOtpViewModel> {
        return VerifyForgotPasswordOtpViewModel::class.java
    }

    override fun getToolbarMenuHandler(): ToolbarMenuHandler? {
        return null
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_verify_otp_forgot_password
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewActions = object : ViewActions {
            override fun navigateBack() {
                activity?.onBackPressed()
            }

            override fun onOtpVerified(message: String) {
                showToast(message)
               // email?.let { getFragmentListener()?.navigateToResetPasswordFragment(it) }

                getFragmentListener()?.navigateToLoginFragment()
            }

            override fun onOtpResent(message: String) {
                showToast(message)
            }
        }

        viewBinding.editOtpEmail1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (!TextUtils.isEmpty(s.toString()) && s.toString().length == 1)
                    viewBinding.editOtpEmail2.requestFocus();
            }
        })
        viewBinding.editOtpEmail1.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus && !TextUtils.isEmpty(viewBinding.editOtpEmail1.getText().toString().trim()))
                viewBinding.editOtpEmail1.setSelection(1)
        })
        viewBinding.editOtpEmail2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (!TextUtils.isEmpty(s.toString()) && s.toString().length == 1)
                    viewBinding.editOtpEmail3.requestFocus();
            }
        })
        viewBinding.editOtpEmail2.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus && !TextUtils.isEmpty(viewBinding.editOtpEmail2.getText().toString().trim()))
                viewBinding.editOtpEmail2.setSelection(1)
        })
        viewBinding.editOtpEmail2.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                if (TextUtils.isEmpty(viewBinding.editOtpEmail2.getText().toString().trim())) {
                    viewBinding.editOtpEmail1.requestFocus()
                    return@OnKeyListener true
                }
            }
            return@OnKeyListener false
        })
        viewBinding.editOtpEmail3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (!TextUtils.isEmpty(s.toString()) && s.toString().length == 1)
                    viewBinding.editOtpEmail4.requestFocus();
            }
        })
        viewBinding.editOtpEmail3.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus && !TextUtils.isEmpty(viewBinding.editOtpEmail3.getText().toString().trim()))
                viewBinding.editOtpEmail3.setSelection(1)
        })
        viewBinding.editOtpEmail3.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                if (TextUtils.isEmpty(viewBinding.editOtpEmail3.getText().toString().trim())) {
                    viewBinding.editOtpEmail2.requestFocus()
                    return@OnKeyListener true
                }
            }
            return@OnKeyListener false
        })

        viewBinding.editOtpEmail4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (!TextUtils.isEmpty(s.toString()) && s.toString().length == 1)
                    viewBinding.editOtpEmail5.requestFocus();
            }
        })
        viewBinding.editOtpEmail4.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus && !TextUtils.isEmpty(viewBinding.editOtpEmail4.getText().toString().trim()))
                viewBinding.editOtpEmail4.setSelection(1)
        })
        viewBinding.editOtpEmail4.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                if (TextUtils.isEmpty(viewBinding.editOtpEmail4.getText().toString().trim())) {
                    viewBinding.editOtpEmail3.requestFocus()
                    return@OnKeyListener true
                }
            }
            return@OnKeyListener false
        })
        viewBinding.editOtpEmail5.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (!TextUtils.isEmpty(s.toString()) && s.toString().length == 1)
                    viewBinding.editOtpEmail6.requestFocus();
            }
        })
        viewBinding.editOtpEmail5.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus && !TextUtils.isEmpty(viewBinding.editOtpEmail5.getText().toString().trim()))
                viewBinding.editOtpEmail5.setSelection(1)
        })
        viewBinding.editOtpEmail5.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                if (TextUtils.isEmpty(viewBinding.editOtpEmail5.getText().toString().trim())) {
                    viewBinding.editOtpEmail4.requestFocus()
                    return@OnKeyListener true
                }
            }
            return@OnKeyListener false
        })

        viewBinding.editOtpEmail6.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                getEdtData()
                viewModel.onContinueClicked();
                true
            }
            false
        })
        viewBinding.editOtpEmail6.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus && !TextUtils.isEmpty(viewBinding.editOtpEmail6.getText().toString().trim()))
                viewBinding.editOtpEmail6.setSelection(1)
        })
        viewBinding.editOtpEmail6.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                if (TextUtils.isEmpty(viewBinding.editOtpEmail6.getText().toString().trim())) {
                    viewBinding.editOtpEmail5.requestFocus()
                    return@OnKeyListener true
                }
            }
            return@OnKeyListener false
        })

        email?.let { viewModel.email = it }


        viewBinding.buttonContinue.setOnClickListener {
            getEdtData()
            viewModel.onContinueClicked() }
        viewBinding.textDontHave.setOnClickListener { viewModel.resendCode() }
        viewBinding.constBack.setOnClickListener { viewModel.navigateToBack() }

    }

   fun  getEdtData(){
       viewModel.otpEmail1=viewBinding.editOtpEmail1.text.toString()
       viewModel.otpEmail2=viewBinding.editOtpEmail2.text.toString()
       viewModel.otpEmail3=viewBinding.editOtpEmail3.text.toString()
       viewModel.otpEmail4=viewBinding.editOtpEmail4.text.toString()
       viewModel.otpEmail5=viewBinding.editOtpEmail5.text.toString()
       viewModel.otpEmail6=viewBinding.editOtpEmail6.text.toString()
       viewModel.password=viewBinding.editPassword.text.toString()
       viewModel.confirmPassword=viewBinding.editConfirmPassword.text.toString()
   }

    override fun showInputError(inputError: InputError) {
        when (inputError.errorType) {
            InputErrorType.OTP_EMAIL_1 -> {
                viewBinding.editOtpEmail1.requestFocus()
                showToast(inputError.message)
            }
            InputErrorType.OTP_EMAIL_2 -> {
                viewBinding.editOtpEmail2.requestFocus()
                showToast(inputError.message)
            }
            InputErrorType.OTP_EMAIL_3 -> {
                viewBinding.editOtpEmail3.requestFocus()
                showToast(inputError.message)
            }
            InputErrorType.OTP_EMAIL_4 -> {
                viewBinding.editOtpEmail4.requestFocus()
                showToast(inputError.message)
            }
            InputErrorType.OTP_EMAIL_5 -> {
                viewBinding.editOtpEmail5.requestFocus()
                showToast(inputError.message)
            }
            InputErrorType.OTP_EMAIL_6 -> {
                viewBinding.editOtpEmail6.requestFocus()
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
        val TAG = "<h521>"
        val ARG_EMAIL = "f643gmail"

        fun newInstance(email: String): VerifyForgotPasswordOtpFragment {
            val bundle = Bundle()
            bundle.putString(ARG_EMAIL, email)
            val frag = VerifyForgotPasswordOtpFragment()
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
        fun onOtpVerified(message: String)
        fun onOtpResent(message: String)
    }
}