package com.virtual.numberkeyboard;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by Dell on 2018/3/14.
 */

public class InputAmountView extends LinearLayout{

    private View root = null;
    EditText et_amount;
    MyKeyBoardView keyboard_view;
    LinearLayout ll_price_select;
    Activity mContext;
    private OnInputCompleteListener mOnInputCompleteListener;

    String amountInt = "";
    private KeyboardUtil mKeyboardUtil;

    public InputAmountView(Context context, @Nullable AttributeSet attrs ) {
        super(context, attrs);
        mContext = (Activity) context;
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        root = inflater.inflate(R.layout.input_amount_view, this);
        et_amount = (root.findViewById(R.id.et_amount));
        keyboard_view = (root.findViewById(R.id.keyboard_view));
        ll_price_select = (root.findViewById(R.id.ll_price_select));

        (root.findViewById(R.id.view_back)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mKeyboardUtil = new KeyboardUtil(mContext);
        mKeyboardUtil.setOnOkClick(new KeyboardUtil.OnOkClick() {
            @Override
            public void onOkClick() {
                ll_price_select.setVisibility(View.GONE);
                amountInt = et_amount.getText().toString();
                if (mOnInputCompleteListener != null) {
                    mOnInputCompleteListener.afterTextChanged(amountInt);
                }
            }
        });

        mKeyboardUtil.setOnCancelClick(new KeyboardUtil.onCancelClick() {
            @Override
            public void onCancellClick() {
                ll_price_select.setVisibility(View.GONE);
                amountInt = et_amount.getText().toString();
                if (mOnInputCompleteListener != null) {
                    mOnInputCompleteListener.afterTextChanged(amountInt);
                }
            }
        });

        et_amount.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mKeyboardUtil.attachTo(et_amount);
                return false;
            }
        });
    }

    public void clickInit(String amountOld) {
        mKeyboardUtil.attachTo(et_amount);
        et_amount.setText(amountOld);
        et_amount.setFocusable(true);
        et_amount.setFocusableInTouchMode(true);
        et_amount.requestFocus();
        ll_price_select.setVisibility(View.VISIBLE);
    }

    public boolean onBackPressed() {

        if (ll_price_select.getVisibility() == View.VISIBLE) {
            ll_price_select.setVisibility(View.GONE);
            return true;
        }else{
            return false;
        }
    }

    public void setOnTextchangedListener(OnInputCompleteListener onInputCompleteListener){
        this.mOnInputCompleteListener = onInputCompleteListener;
    }

    public interface OnInputCompleteListener {
        void afterTextChanged(String amount);
    }
}
