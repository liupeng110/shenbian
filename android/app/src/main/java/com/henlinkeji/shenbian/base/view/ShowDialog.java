package com.henlinkeji.shenbian.base.view;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.henlinkeji.shenbian.R;
import com.henlinkeji.shenbian.base.callback.OperationCallback;


/**
 * Created by Miracler on 17/2/9.
 */

public class ShowDialog {

    public static void showSelectPopup(Activity activity, int title, String message, int okText, int cancelText, final OperationCallback okCallback, final OperationCallback cancelCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        View view = activity.getLayoutInflater().inflate(R.layout.dialog_select_popup_layout, null);

        TextView titleTextView = (TextView) view.findViewById(R.id.popup_title);
        titleTextView.setText(title);

        TextView messageTextView = (TextView) view.findViewById(R.id.popup_message);
        messageTextView.setText(message);

        TextView okTextView = (TextView) view.findViewById(R.id.popup_ok);
        okTextView.setText(okText);

        TextView cancelTextView = (TextView) view.findViewById(R.id.popup_cancel);
        cancelTextView.setText(cancelText);

        builder.setView(view);

        final Dialog dialog = builder.create();
        okTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                if (okCallback != null) {
                    okCallback.execute();
                }
            }
        });

        cancelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                if (cancelCallback != null) {
                    cancelCallback.execute();
                }
            }
        });

        dialog.show();
    }

    public static Dialog showSelectNoTitlePopup(Activity activity, String message, int okText, int cancelText, final OperationCallback okCallback, final OperationCallback cancelCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        View view = activity.getLayoutInflater().inflate(R.layout.dialog_select_no_title_popup_layout, null);

        TextView messageTextView = (TextView) view.findViewById(R.id.popup_message);
        messageTextView.setText(message);

        TextView okTextView = (TextView) view.findViewById(R.id.popup_ok);
        okTextView.setText(okText);

        TextView cancelTextView = (TextView) view.findViewById(R.id.popup_cancel);
        cancelTextView.setText(cancelText);

        builder.setView(view);

        final Dialog dialog = builder.create();
        okTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                if (okCallback != null) {
                    okCallback.execute();
                }
            }
        });

        cancelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                if (cancelCallback != null) {
                    cancelCallback.execute();
                }
            }
        });

        dialog.show();
        return dialog;
    }

    public static Dialog showTipPopup(Activity activity, String message, int okText, final OperationCallback okCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        View view = activity.getLayoutInflater().inflate(R.layout.dialog_tip_popup_layout, null);

        TextView messageTextView = (TextView) view.findViewById(R.id.popup_message);
        messageTextView.setText(message);

        TextView okTextView = (TextView) view.findViewById(R.id.popup_ok);
        okTextView.setText(okText);

        builder.setView(view);

        final Dialog dialog = builder.create();
        okTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                if (okCallback != null) {
                    okCallback.execute();
                }
            }
        });

        dialog.show();
        return dialog;
    }

}
