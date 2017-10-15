package com.suda.openpayshortcut.activity;

import android.didikee.donate.AlipayDonate;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.suda.openpayshortcut.R;
import com.suda.openpayshortcut.util.RootShell;

import java.io.DataOutputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showGithub();
    }

    public void donateAlipay(View view) {
        boolean hasInstalledAlipayClient = AlipayDonate.hasInstalledAlipayClient(this);
        if (hasInstalledAlipayClient) {
            AlipayDonate.startAlipayClient(this, "apqiqql0hgh5pmv54d");
        }
    }

    public void getSu(View view) {
        RootShell rootShell = RootShell.open();
        rootShell.execute("su");
        rootShell.close();
    }


    private void showGithub(){
        ((TextView)findViewById(R.id.github_tv)).setText(Html.fromHtml("Github 项目地址：<a href=\"https://github.com/ghbhaha/OpenPayShortcut\">https://github.com/ghbhaha/OpenPayShortcut</a>"));
        ((TextView)findViewById(R.id.github_tv)).setMovementMethod(LinkMovementMethod.getInstance());
    }

}
