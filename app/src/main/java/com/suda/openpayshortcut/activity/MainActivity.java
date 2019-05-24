package com.suda.openpayshortcut.activity;

import android.content.Intent;
import android.didikee.donate.AlipayDonate;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.jaredrummler.android.shell.Shell;
import com.suda.openpayshortcut.R;

import static com.suda.openpayshortcut.util.AccessibilityUtil.isAccessibilitySettingsOn;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showGithub();
        TextView textView = (TextView) findViewById(R.id.getAccessibilityStatus);
        textView.setText((isAccessibilitySettingsOn(this) ? "已" : "未") + "辅助权限");

    }

    public void donateAlipay(View view) {
        boolean hasInstalledAlipayClient = AlipayDonate.hasInstalledAlipayClient(this);
        if (hasInstalledAlipayClient) {
            AlipayDonate.startAlipayClient(this, "apqiqql0hgh5pmv54d");
        }
    }

    public void getSu(View view) {
        Shell.SU.available();
    }

    public void getAccessibility(View view) {
        if (!isAccessibilitySettingsOn(this)) {
            try {
                startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
            } catch (Exception e) {
                startActivity(new Intent(Settings.ACTION_SETTINGS));
                e.printStackTrace();
            }
        }
    }

    private void showGithub(){
        ((TextView)findViewById(R.id.github_tv)).setText(Html.fromHtml("Github 项目地址：<a href=\"https://github.com/ghbhaha/OpenPayShortcut\">https://github.com/ghbhaha/OpenPayShortcut</a>"));
        ((TextView)findViewById(R.id.github_tv)).setMovementMethod(LinkMovementMethod.getInstance());
    }

}
