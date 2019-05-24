package com.suda.openpayshortcut.activity.pay;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import static com.suda.openpayshortcut.util.AccessibilityUtil.isAccessibilitySettingsOn;

public abstract class BasePayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openPay();
        finish();
    }

    protected boolean getAccessibility() {
        if (!isAccessibilitySettingsOn(this)) {
            try {
                startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
            } catch (Exception e) {
                startActivity(new Intent(Settings.ACTION_SETTINGS));
                e.printStackTrace();
            }
            Toast.makeText(this, "请授予辅助权限", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public abstract void openPay();
}
