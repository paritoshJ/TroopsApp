package com.social.troops.newWork.auth.login

import android.content.Context
import android.text.TextUtils
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.social.troops.R
import com.social.troops.newWork.domain.utils.Utils


class GetUserEmailDialog(val context: Context, val dialogListener: GetUserEmailDialogListener) {

    private var dialog: AlertDialog? = null
    private var dialogView: View? = null
    private var buttonSubmit: Button? = null
    private var editEmail: EditText? = null
    private var textMessage: TextView? = null

    init {
        initDialog()
    }

    fun initDialog() {
        val builder = AlertDialog.Builder(context)
        dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_forgot_password, null)

        buttonSubmit = dialogView!!.findViewById(R.id.btnSubmit)
        textMessage = dialogView!!.findViewById(R.id.text_message)
        editEmail = dialogView!!.findViewById(R.id.edit_email)

        editEmail!!.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    onSubmit()
                    return true
                }
                return false
            }
        })
        buttonSubmit!!.setOnClickListener(View.OnClickListener {
            onSubmit()
        })

        builder.setView(dialogView)
        dialog = builder.create()
    }

    fun setMessage(message: String) {
        message?.let {
            textMessage?.visibility = View.VISIBLE
            textMessage?.setText(message)
        }
    }

    private fun onSubmit() {
        Utils.closeKeyBoard(context, dialogView)
        val email = editEmail!!.text.toString().trim { it <= ' ' }
        if (TextUtils.isEmpty(email)) {
            dialogListener?.showToast(context.getString(R.string.err_email_empty))
            editEmail!!.requestFocus()
            return
        }
        if (!Utils.isValidEmail(email)) {
            dialogListener?.showToast(context.getString(R.string.err_email_invalid))
            editEmail!!.requestFocus()
            return
        }
        dialogListener?.onEmailSubmit(email)
        dialog!!.dismiss()
    }

    fun show() {
        dialog!!.show()
    }

    interface GetUserEmailDialogListener {
        fun onEmailSubmit(email: String)
        fun showToast(message: String)
    }
}