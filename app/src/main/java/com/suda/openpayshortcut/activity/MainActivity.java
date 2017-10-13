package com.suda.openpayshortcut.activity;

import android.didikee.donate.AlipayDonate;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.suda.openpayshortcut.R;
import com.suda.openpayshortcut.util.RootShell;

import java.io.DataOutputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

    private void execShell(String paramString) {
        try {
            OutputStream outputStream = Runtime.getRuntime().exec("su").getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeBytes(paramString);
            dataOutputStream.flush();
            dataOutputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
