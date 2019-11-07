package com.social.troops.newWork.domain.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.social.troops.newWork.data.RepoListener
import com.social.troops.newWork.domain.dialogs.ProgressDialog
import com.social.troops.newWork.domain.utils.Utils
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy


abstract class BaseFragment<T : BaseViewModel, B : ViewDataBinding> : Fragment(), RepoListener {

    private var toolbar: Toolbar? = null
    abstract fun getToolbarMenuHandler(): ToolbarMenuHandler?
    @LayoutRes
    abstract fun getLayoutResource(): Int

    lateinit var viewModel: T
    lateinit var viewBinding: B

    abstract fun getViewModelClass(): Class<T>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = DataBindingUtil.inflate(inflater, getLayoutResource(), container, false)
        return viewBinding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(getViewModelClass())
        viewModel.repoListener = this
        viewBinding.setVariable(getBindingVariable(), viewModel)
        viewBinding.setLifecycleOwner(this)
        viewBinding.executePendingBindings()
        getToolbarMenuHandler()?.apply {
            setHasOptionsMenu(true)
            toolbar = view.findViewById(this.toolbarId)
            (activity as AppCompatActivity).setSupportActionBar(toolbar)
            if (TextUtils.isEmpty(this.toolbarTitle))
                (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
            else {
                if (this.toolbarTitleId == 0) {
                    (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(true)
                    (activity as AppCompatActivity).supportActionBar?.title = this.toolbarTitle
                } else {
                    (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
                    (toolbar?.findViewById(this.toolbarTitleId) as TextView).text = this.toolbarTitle
                }
            }
            if (this.hasBackButton()) {
                (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
                (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
                toolbar?.setNavigationOnClickListener({ activity?.onBackPressed() })
            }
        }

        viewModel.showToast.observe(viewLifecycleOwner, Observer { showToast(message = it) })
        viewModel.showInputError.observe(viewLifecycleOwner, Observer { showInputError(inputError = it) })
        viewModel.closeKeyBoard.observe(viewLifecycleOwner, Observer { closeKeyboard() })
    }

    abstract fun getBindingVariable(): Int

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        getToolbarMenuHandler()?.apply {
            if (this.hasMenu() && this.menuResource == 0)
                throw RuntimeException("You haven't specified menu resource for the fragment toolbar!")
            if (this.hasMenu())
                inflater.inflate(getToolbarMenuHandler()?.menuResource!!, menu)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    fun overrideBackButtonClickListener(onClickListener: View.OnClickListener) {
        getToolbarMenuHandler()?.apply {
            if (this.hasBackButton()) toolbar?.setNavigationOnClickListener(onClickListener)
        }
    }

    fun setToolbarTitle(title: String) {
        getToolbarMenuHandler()?.apply {
            if (TextUtils.isEmpty(this.toolbarTitle))
                (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
            else {
                if (this.toolbarTitleId == 0) {
                    (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(true)
                    (activity as AppCompatActivity).supportActionBar?.title = this.toolbarTitle
                } else {
                    (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
                    (toolbar?.findViewById(this.toolbarTitleId) as TextView).text = this.toolbarTitle
                }
            }
        }
    }

//    fun setToolbarTitleClickListener(toolbarTitleClickListener: View.OnClickListener) {
//        (toolbar?.findViewById(R.id.toolbarTitle) as TextView).setOnClickListener(toolbarTitleClickListener)
//    }

    fun setToolbarBackButtonIcon(@DrawableRes icon: Int) {
        toolbar?.setNavigationIcon(icon)
    }

    fun hideNavigationButton() {
        (activity as AppCompatActivity).getSupportActionBar()?.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity).getSupportActionBar()?.setDisplayShowHomeEnabled(false)
    }

    fun closeKeyboard() {
        Utils.closeKeyBoard(context, view)
    }

    fun showToast(message: String) {
        if (context != null && !TextUtils.isEmpty(message))
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    open fun showInputError(inputError: InputError) {}

    // pass @ToolbarMenuHandler as null if there is no toolbar
    interface ToolbarMenuHandler {
        // Toolbar id necessary
        @get:IdRes
        val toolbarId: Int
        // Toolbar title id if custom toolbar title is there OR pass 0 if default title area is being used
        @get:IdRes
        val toolbarTitleId: Int
        // Toolbar menu resource if hasToolbar
        @get:MenuRes
        val menuResource: Int
        // Toolbar title
        val toolbarTitle: String

        // True if toolbar has a menu and also provide menu resource
        fun hasMenu(): Boolean

        // Will toolbar should show back button by default
        fun hasBackButton(): Boolean
    }

    override fun onDataRequestFailed(dataRequestType: Int, statusCode: Int, message: String) {
        showToast(message)
    }

    override fun onNetworkConnectionError(dataRequestType: Int, message: String) {
        showToast(message)
    }

    override fun setDataRequestProgressIndicator(dataRequestType: Int, visible: Boolean) {
//        showToast("setDataRequestProgressIndicator : " + visible)
        if (visible) showProgressDialog()
        else dismissProgressDialog()
    }

    data class InputError(val errorType: Int, val message: String)

    @Retention(RetentionPolicy.SOURCE)
    @IntDef(
            InputErrorType.EMAIL, InputErrorType.PASSWORD, InputErrorType.FIRST_NAME, InputErrorType.LAST_NAME,
            InputErrorType.CONFIRM_PASSWORD, InputErrorType.MISMATCH_PASSWORD, InputErrorType.MOBILE,
            InputErrorType.OTP,
            InputErrorType.OTP_EMAIL_1,
            InputErrorType.OTP_EMAIL_2,
            InputErrorType.OTP_EMAIL_3,
            InputErrorType.OTP_EMAIL_4,
            InputErrorType.OTP_EMAIL_5,
            InputErrorType.OTP_EMAIL_6,
            InputErrorType.NEW_PASSWORD,
            InputErrorType.OTP_MOBILE_1,
            InputErrorType.OTP_MOBILE_2,
            InputErrorType.OTP_MOBILE_3,
            InputErrorType.OTP_MOBILE_4,
            InputErrorType.OTP_MOBILE_5,
            InputErrorType.OTP_MOBILE_6
    )
    annotation class InputErrorType {
        companion object {
            const val EMAIL = 1
            const val PASSWORD = 2
            const val FIRST_NAME = 3
            const val LAST_NAME = 4
            const val CONFIRM_PASSWORD = 5
            const val MISMATCH_PASSWORD = 6
            const val MOBILE = 7
            const val OTP = 8

            const val OTP_EMAIL_1 = 9
            const val OTP_EMAIL_2 = 10
            const val OTP_EMAIL_3 = 11
            const val OTP_EMAIL_4 = 12
            const val OTP_EMAIL_5 = 13
            const val OTP_EMAIL_6 = 14

            const val NEW_PASSWORD = 15

            const val OTP_MOBILE_1 = 16
            const val OTP_MOBILE_2 = 17
            const val OTP_MOBILE_3 = 18
            const val OTP_MOBILE_4 = 19
            const val OTP_MOBILE_5 = 20
            const val OTP_MOBILE_6 = 21

            const val AMOUNT = 22
            const val ACCOUNT_INFO = 23
            const val MESSAGE = 24

            const val REFERAL_CODE = 25

        }
    }

    private var progressDialog: ProgressDialog? = null

    fun getProgressDialog(): ProgressDialog? {
        return progressDialog
    }

    fun showProgressDialog() {
        if (progressDialog != null && progressDialog!!.getDialog().isShowing()) return
        progressDialog = ProgressDialog(context)
        progressDialog!!.show()
    }

    fun showProgressDialog(message: String) {
        if (progressDialog != null && progressDialog!!.getDialog().isShowing()) return
        progressDialog = ProgressDialog(context, message)
        progressDialog!!.show()
    }

    fun showProgressDialog(@NonNull progressDialogListener: ProgressDialog.ProgressDialogListener) {
        if (progressDialog != null && progressDialog!!.getDialog().isShowing()) return
        progressDialog = ProgressDialog(context, progressDialogListener)
        progressDialog!!.show()
    }

    fun showProgressDialog(message: String,
                           @NonNull progressDialogListener: ProgressDialog.ProgressDialogListener) {
        if (progressDialog != null && progressDialog!!.getDialog().isShowing()) return
        progressDialog = ProgressDialog(context, message, progressDialogListener)
        progressDialog!!.show()
    }

    fun dismissProgressDialog() {
        if (progressDialog != null && progressDialog!!.getDialog().isShowing())
            progressDialog!!.getDialog().dismiss()
    }
}