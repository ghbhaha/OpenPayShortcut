package com.suda.openpayshortcut.activity.pay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BasePayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openPay();
        finish();
    }

    public abstract void openPay();
}
