package com.azzdorfrobotics.android.testio.features.shared.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.TextView;
import com.azzdorfrobotics.android.testio.R;

/**
 * Created on 04.01.2018.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */

public class SnackBarUtil {
    public static void styleSnackBar(Snackbar snackbar, Context context) {
        try {
            TextView snackbarActionTextView = (TextView) snackbar.getView()
                .findViewById(android.support.design.R.id.snackbar_action);
            snackbarActionTextView.setTextColor(
                ContextCompat.getColor(context, R.color.colorAccent));
            TextView snackbarTextView = (TextView) snackbar.getView()
                .findViewById(android.support.design.R.id.snackbar_text);
            snackbarTextView.setTextColor(ContextCompat.getColor(context, R.color.white));
            snackbarTextView.setMaxLines(3);
        } catch (Exception e1) {
            Log.e("SnackBarUtil", "Failed to style snackbar");
        }
    }
}
