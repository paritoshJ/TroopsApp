package com.social.troops.newWork.auth.verify_otp.register

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.social.troops.R
import com.social.troops.databinding.FragmentVerifyOtpRegisterBinding
import com.social.troops.newWork.AppManager
import com.social.troops.newWork.auth.base.AuthBaseFragment
import com.social.troops.newWork.data.RepoListener
import com.social.troops.newWork.domain.models.User
import java.util.concurrent.TimeUnit
import java.text.DecimalFormat


class VerifyRegistrationOtpFragment : AuthBaseFragment<VerifyRegistrationOtpViewModel, FragmentVerifyOtpRegisterBinding>(),
        RepoListener {

    var email: String? = null
    var mobile: String? = null
    var user: User? = null


    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getViewModelClass(): Class<VerifyRegistrationOtpViewModel> {
        return VerifyRegistrationOtpViewModel::class.java
    }

    override fun getToolbarMenuHandler(): ToolbarMenuHandler? {
        return null
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_verify_otp_register
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewActions = object : ViewActions {
            override fun navigateBack() {
                activity?.onBackPressed()
            }

            override fun onOtpVerified(message: String) {
                showToast(message)
                AppManager.setUser(user)

                showRefDialog()

            }

            override fun onOtpResent(message: String) {
                showToast(message)
                startResendOtpTimer()
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


        viewBinding.editOtpMobile1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (!TextUtils.isEmpty(s.toString()) && s.toString().length == 1)
                    viewBinding.editOtpMobile2.requestFocus();
            }
        })
        viewBinding.editOtpMobile1.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus && !TextUtils.isEmpty(viewBinding.editOtpMobile1.getText().toString().trim()))
                viewBinding.editOtpMobile1.setSelection(1)
        })
        viewBinding.editOtpMobile2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (!TextUtils.isEmpty(s.toString()) && s.toString().length == 1)
                    viewBinding.editOtpMobile3.requestFocus();
            }
        })
        viewBinding.editOtpMobile2.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus && !TextUtils.isEmpty(viewBinding.editOtpMobile2.getText().toString().trim()))
                viewBinding.editOtpMobile2.setSelection(1)
        })
        viewBinding.editOtpMobile2.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                if (TextUtils.isEmpty(viewBinding.editOtpMobile2.getText().toString().trim())) {
                    viewBinding.editOtpMobile1.requestFocus()
                    return@OnKeyListener true
                }
            }
            return@OnKeyListener false
        })
        viewBinding.editOtpMobile3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (!TextUtils.isEmpty(s.toString()) && s.toString().length == 1)
                    viewBinding.editOtpMobile4.requestFocus();
            }
        })
        viewBinding.editOtpMobile3.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus && !TextUtils.isEmpty(viewBinding.editOtpMobile3.getText().toString().trim()))
                viewBinding.editOtpMobile3.setSelection(1)
        })
        viewBinding.editOtpMobile3.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                if (TextUtils.isEmpty(viewBinding.editOtpMobile3.getText().toString().trim())) {
                    viewBinding.editOtpMobile2.requestFocus()
                    return@OnKeyListener true
                }
            }
            return@OnKeyListener false
        })

        viewBinding.editOtpMobile4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (!TextUtils.isEmpty(s.toString()) && s.toString().length == 1)
                    viewBinding.editOtpMobile5.requestFocus();
            }
        })
        viewBinding.editOtpMobile4.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus && !TextUtils.isEmpty(viewBinding.editOtpMobile4.getText().toString().trim()))
                viewBinding.editOtpMobile4.setSelection(1)
        })
        viewBinding.editOtpMobile4.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                if (TextUtils.isEmpty(viewBinding.editOtpMobile4.getText().toString().trim())) {
                    viewBinding.editOtpMobile3.requestFocus()
                    return@OnKeyListener true
                }
            }
            return@OnKeyListener false
        })
        viewBinding.editOtpMobile5.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (!TextUtils.isEmpty(s.toString()) && s.toString().length == 1)
                    viewBinding.editOtpMobile6.requestFocus();
            }
        })
        viewBinding.editOtpMobile5.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus && !TextUtils.isEmpty(viewBinding.editOtpMobile5.getText().toString().trim()))
                viewBinding.editOtpMobile5.setSelection(1)
        })
        viewBinding.editOtpMobile5.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                if (TextUtils.isEmpty(viewBinding.editOtpMobile5.getText().toString().trim())) {
                    viewBinding.editOtpMobile4.requestFocus()
                    return@OnKeyListener true
                }
            }
            return@OnKeyListener false
        })

        viewBinding.editOtpMobile6.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                viewModel.onContinueClicked();
                true
            }
            false
        })
        viewBinding.editOtpMobile6.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus && !TextUtils.isEmpty(viewBinding.editOtpMobile6.getText().toString().trim()))
                viewBinding.editOtpMobile6.setSelection(1)
        })
        viewBinding.editOtpMobile6.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                if (TextUtils.isEmpty(viewBinding.editOtpMobile6.getText().toString().trim())) {
                    viewBinding.editOtpMobile5.requestFocus()
                    return@OnKeyListener true
                }
            }
            return@OnKeyListener false
        })






        email?.let { viewModel.email = it }
        mobile?.let { viewModel.mobile = it }
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

            InputErrorType.OTP_MOBILE_1 -> {
                viewBinding.editOtpMobile1.requestFocus()
                showToast(inputError.message)
            }
            InputErrorType.OTP_MOBILE_2 -> {
                viewBinding.editOtpMobile2.requestFocus()
                showToast(inputError.message)
            }
            InputErrorType.OTP_MOBILE_3 -> {
                viewBinding.editOtpMobile3.requestFocus()
                showToast(inputError.message)
            }
            InputErrorType.OTP_MOBILE_4 -> {
                viewBinding.editOtpMobile4.requestFocus()
                showToast(inputError.message)
            }
            InputErrorType.OTP_MOBILE_5 -> {
                viewBinding.editOtpMobile5.requestFocus()
                showToast(inputError.message)
            }
            InputErrorType.OTP_MOBILE_6 -> {
                viewBinding.editOtpMobile6.requestFocus()
                showToast(inputError.message)
            }
        }
    }

    companion object {
        val TAG = "<h521>"
        val ARG_EMAIL = "email5"
        val ARG_MOBILE = "mob5"
        val ARG_USER = "user732"

        fun newInstance(email: String, mobile: String, user: User): VerifyRegistrationOtpFragment {
            val bundle = Bundle()
            bundle.putString(ARG_EMAIL, email)
            bundle.putString(ARG_MOBILE, mobile)
            bundle.putParcelable(ARG_USER, user)
            val frag = VerifyRegistrationOtpFragment()
            frag.arguments = bundle
            return frag
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey(ARG_EMAIL)) email = it.getString(ARG_EMAIL)
            if (it.containsKey(ARG_MOBILE)) mobile = it.getString(ARG_MOBILE)
            if (it.containsKey(ARG_USER)) user = it.getParcelable(ARG_USER)
        }
    }

    private var timer: CountDownTimer? = null
    var formatter = DecimalFormat("00")

    //    private val resendOtpHandler = Handler()
//
//    private val resendOtpRunnable = Runnable {
//        viewBinding.textTime.setText("00:00")
//        viewBinding.constResendTimer.visibility = View.GONE
//    }
//
    fun startResendOtpTimer() {
        timer = timer((5 * 60 * 1000), 1000)
        timer!!.start()
        viewBinding.textDontHave.visibility = View.GONE
        viewBinding.constResendTimer.visibility = View.VISIBLE
//        resendOtpHandler.removeCallbacksAndMessages(null)
//        resendOtpHandler.postDelayed(resendOtpRunnable, (5 * 60 * 1000))
    }

    //
    fun stopResendOtpTimer() {
        timer?.let {
            it.cancel()
            timer = null
        }
    }

    override fun onDestroyView() {
        stopResendOtpTimer()
        super.onDestroyView()
    }

    // Method to configure and return an instance of CountDownTimer object
    private fun timer(millisInFuture: Long, countDownInterval: Long): CountDownTimer {
        return object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {

                viewBinding.textTime.setText("".plus(formatter.format(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))).plus(":")
                        .plus(
                                formatter.format((TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))))
                        ))


//                val timeRemaining = timeString(millisUntilFinished)
//                if (isCancelled) {
//                    text_view.text = "${text_view.text}\nStopped.(Cancelled)"
//                    cancel()
//                } else {
//                    text_view.text = timeRemaining
//                }

            }

            override fun onFinish() {
                viewBinding.constResendTimer.visibility = View.GONE
                viewBinding.textDontHave.visibility = View.VISIBLE
            }
        }
    }

    interface ViewActions {
        fun navigateBack()
        fun onOtpVerified(message: String)
        fun onOtpResent(message: String)
    }

    fun showRefDialog(){
        val builder = activity?.let { AlertDialog.Builder(it) }
        builder?.setTitle(getString(R.string.app_name))
        builder?.setMessage("Do you have any Referral code?")
        //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder?.setPositiveButton(android.R.string.yes) { dialog, which ->


            getFragmentListener()?.navigateToRefralFragment(user?.email!!)


        }

        builder?.setNegativeButton(android.R.string.no) { dialog, which ->
            getFragmentListener()?.navigateToHomeActivity()

        }

        builder?.setNeutralButton("") { dialog, which ->
        }
        builder?.show()
    }
}