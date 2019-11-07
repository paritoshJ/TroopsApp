package com.social.troops.newWork.domain.dialogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AlertDialog;

/**
 * The type Base dialog.
 */
public abstract class BaseDialog {

    private AlertDialog dialog;
    private View view;

    /**
     * Gets context.
     *
     * @return the context
     */
    protected abstract Context getContext();

    /**
     * Gets layout resource.
     *
     * @return the layout resource
     */
    protected abstract @LayoutRes
    int getLayoutResource();

    /**
     * Gets dialog style.
     *
     * @return the dialog style
     */
    protected abstract @StyleRes
    int getDialogStyle();

    /**
     * Show.
     */
    protected abstract void show();

    /**
     * Init dialog.
     */
    public void initDialog() {
        getDialog();
    }

    /**
     * Gets dialog.
     *
     * @return the dialog
     */
    public AlertDialog getDialog() {
        if (dialog == null) {
            if (getContext() == null) throw new RuntimeException("Context cannot be null!");
            AlertDialog.Builder builder;
            if (getDialogStyle() == 0)
//                builder = new AlertDialog.Builder(getContext(), R.style.AppTheme_Dialog);
                builder = new AlertDialog.Builder(getContext());
            else builder = new AlertDialog.Builder(getContext(), getDialogStyle());
            if (getLayoutResource() != 0) {
                view = LayoutInflater.from(getContext()).inflate(getLayoutResource(), null);
                builder.setView(view);
            }
            dialog = builder.create();
        }
        return dialog;
    }

    /**
     * Gets view.
     *
     * @return the view
     */
    public View getView() {
        return view;
    }
}