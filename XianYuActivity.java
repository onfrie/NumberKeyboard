package com.virtual.numberkeyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by xuejinwei on 16/8/19.
 * Email:xuejinwei@outlook.com
 */
public class XianYuActivity extends AppCompatActivity {

    LinearLayout ll_price;
    TextView tv_price;
    InputAmountView inputAmountView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xianyu);

        ll_price = findViewById(R.id.ll_price);
        tv_price = findViewById(R.id.tv_price);
        inputAmountView = findViewById(R.id.inputAmountView);
        inputAmountView.setOnTextchangedListener(new InputAmountView.OnInputCompleteListener() {
            @Override
            public void afterTextChanged(String amount) {
                ll_price.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                tv_price.setText(amount);
            }
        });

        ll_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputAmountView.clickInit(tv_price.getText().toString());
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (!inputAmountView.onBackPressed()) {
            super.onBackPressed();
        }
    }
}
