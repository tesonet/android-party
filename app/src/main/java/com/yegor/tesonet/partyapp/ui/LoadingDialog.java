package com.yegor.tesonet.partyapp.ui;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.yegor.tesonet.partyapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Full screen loading dialog
 */
public class LoadingDialog extends DialogFragment {

    private static final String ARG_KEY = "ARG_KEY";
    private Unbinder unbinder;

    @BindView(R.id.text)
    TextView messageView;

    public static LoadingDialog newInstance(String text) {
        LoadingDialog f = new LoadingDialog();
        Bundle args = new Bundle();
        args.putString(ARG_KEY, text);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null && dialog.getWindow() != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().setBackgroundDrawable(null);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        unbinder = ButterKnife.bind(this, view);
        Bundle args = getArguments();
        String text = args.getString(ARG_KEY);
        messageView.setText(text);
        return view;
    }

    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }
}
