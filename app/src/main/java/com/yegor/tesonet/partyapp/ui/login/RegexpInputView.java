package com.yegor.tesonet.partyapp.ui.login;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.View;

import com.yegor.tesonet.partyapp.R;
import com.yegor.tesonet.partyapp.framework.TextWatcherStub;

/**
 * View that checks itself with regular expression
 */
public class RegexpInputView extends TextInputLayout {

    private TextInputEditText mEditText;
    private String mHint;
    private String mRegexp;
    private String mError;
    private Drawable mImage;
    private boolean mCurrentStatus = false;
    private TextStatusChangedListener mListener;

    public RegexpInputView(Context context) {
        this(context, null);
    }

    public RegexpInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    public RegexpInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_input, this);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RegexpInputView, 0, 0);
        try {
            mHint = a.getString(R.styleable.RegexpInputView_hint);
            mRegexp = a.getString(R.styleable.RegexpInputView_regexp);
            mError = a.getString(R.styleable.RegexpInputView_error);
            mImage = a.getDrawable(R.styleable.RegexpInputView_image);
        } finally {
            a.recycle();
        }
        initEditText();
    }

    private void initEditText() {
        mEditText = (TextInputEditText) findViewById(R.id.input);
        mEditText.setHint(mHint);
        mEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(mImage, null, null, null);
        mEditText.addTextChangedListener(new TextWatcherStub() {

            @Override
            public void afterTextChanged(Editable s) {
                boolean newStatus = checkText();
                if (mListener != null) {
                    mListener.onNewTextStatus(newStatus);
                }
                mCurrentStatus = newStatus;
            }
        });
        mEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!mCurrentStatus && !hasFocus) {
                    setError(mError);
                } else {
                    setError(null);
                }
            }
        });
    }

    private boolean checkText() {

        String text = mEditText.getText().toString();
        return text.matches(mRegexp);
    }

    /**
     * @param listener to listen status changed events
     */
    public void setStatusListener(TextStatusChangedListener listener) {
        mListener = listener;
    }

    /**
     * @return current text in input field
     */
    public String getText() {
        return mEditText.getText().toString();
    }

    /**
     * @return is text matches regexp now
     */
    public boolean getStatus() {
        return mCurrentStatus;
    }

    /**
     * interface to listen text validation events
     */
    public interface TextStatusChangedListener {

        /**
         * calls on current status changes
         *
         * @param status new status
         */
        void onNewTextStatus(boolean status);
    }

}
