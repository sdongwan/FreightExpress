package com.highspace.hs.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.highspace.hs.R;

/**
 * Created by Administrator on 2016/9/18.
 */
public class LoadProgressDialog extends Dialog {


    public LoadProgressDialog(Context context) {
        super(context);
    }

    public LoadProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LoadProgressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static LoadProgressDialog Create(Context context, String message) {
        LoadProgressDialog loadProgressDialog = new LoadProgressDialog(context, R.style.Progress_Dialog_Style);
        View dialogContentView = LayoutInflater.from(context).inflate(R.layout.dialog_load, null);
        TextView tv = (TextView) dialogContentView.findViewById(R.id.dialog_message);
        tv.setText(message + "");
        loadProgressDialog.setContentView(dialogContentView);
        return loadProgressDialog;
    }


}
