package com.social.troops.newWork.domain.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.app.marketplace.sellBuyDevice.R;


public class DownloadProgressDialog extends BaseDialog {

    private Context context;
    private ProgressDialogListener progressDialogListener;
    private ProgressBar progressBar;
    private TextView textMessage;
    private String message;

    private DownloadProgressDialog() {
    }

    /**
     * Instantiates a new Progress dialog.
     *
     * @param context the context
     */
    public DownloadProgressDialog(Context context) {
        this.context = context;
        initDialog();
        initViews();
    }

    /**
     * Instantiates a new Progress dialog.
     *
     * @param context the context
     * @param message the message
     */
    public DownloadProgressDialog(Context context, String message) {
        this.context = context;
        this.message = message;
        initDialog();
        initViews();
    }

    /**
     * Instantiates a new Progress dialog.
     *
     * @param context                the context
     * @param progressDialogListener the progress dialog listener
     */
    public DownloadProgressDialog(Context context, @NonNull ProgressDialogListener progressDialogListener) {
        this.context = context;
        this.progressDialogListener = progressDialogListener;
        initDialog();
        initViews();
    }

    /**
     * Instantiates a new Progress dialog.
     *
     * @param context                the context
     * @param message                the message
     * @param progressDialogListener the progress dialog listener
     */
    public DownloadProgressDialog(Context context, String message, @NonNull ProgressDialogListener progressDialogListener) {
        this.context = context;
        this.message = message;
        this.progressDialogListener = progressDialogListener;
        initDialog();
        initViews();
    }

    /**
     * Init views.
     */
    protected void initViews() {
        if (getView() == null) return;
        progressBar = getView().findViewById(R.id.prog);
        textMessage = getView().findViewById(R.id.txtMessage);
        setProgressMessage(message);
        if (progressDialogListener != null) {
            getDialog().setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    progressDialogListener.onProgressDialogCanceled();
                }
            });
        }
    }

    @Override
    public void show() {
        try {
            getDialog().setCancelable(false);
            getDialog().setCanceledOnTouchOutside(false);
            getDialog().getWindow().setDimAmount(0.7f);
            getDialog().show();
        } catch (Exception e) {
        }
    }

    /**
     * Sets progress message.
     *
     * @param message the message
     */
    public void setProgressMessage(String message) {
        if (TextUtils.isEmpty(message)) textMessage.setText(R.string.msg_please_wait);
        else textMessage.setText(message);
    }

    @Override
    protected Context getContext() {
        return context;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.dialog_download_progress;
    }

    @Override
    protected int getDialogStyle() {
        return R.style.AppTheme_NoBackDialog;
    }

    public void setDownloadProgress(int progress) {
        if (progressBar != null) progressBar.setProgress(progress);
    }

    /**
     * The interface Progress dialog listener.
     */
    public interface ProgressDialogListener {
        /**
         * On progress dialog canceled.
         */
        void onProgressDialogCanceled();
    }
}