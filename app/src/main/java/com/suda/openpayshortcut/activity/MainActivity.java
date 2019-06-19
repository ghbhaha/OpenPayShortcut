package com.suda.openpayshortcut.activity;

import android.content.Intent;
import android.didikee.donate.AlipayDonate;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jaredrummler.android.shell.Shell;
import com.suda.openpayshortcut.R;
import com.suda.openpayshortcut.service.WeixinWidget;
import com.suda.openpayshortcut.util.BitmapUtil;
import com.suda.openpayshortcut.util.SharePreferenceUtil;

import static com.suda.openpayshortcut.util.AccessibilityUtil.isAccessibilitySettingsOn;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showGithub();
        TextView textView = (TextView) findViewById(R.id.getAccessibilityStatus);
        textView.setText((isAccessibilitySettingsOn(this) ? "已" : "未") + "辅助权限");
        refreshCorner();
        refreshAlpha();
    }

    private void refreshAlpha() {
        float progress = (SharePreferenceUtil.getSharePreferenceUtil(this).getValue("alpha", 1f)) * 1000;
        ((AppCompatSeekBar) findViewById(R.id.bg_alpha_seek)).setProgress((int) progress);
        ((AppCompatSeekBar) findViewById(R.id.bg_alpha_seek)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                findViewById(R.id.widget_bg).setAlpha(progress * 1f / 1000);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharePreferenceUtil.getSharePreferenceUtil(MainActivity.this).putValue("alpha", (seekBar.getProgress() * 1f / 1000f));
                Intent intent = new Intent();
                intent.setAction(WeixinWidget.WIDGET_BROADCAST);
                sendBroadcast(intent);
            }
        });
    }

    private void refreshCorner() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.alipay_fukuan, options);
        final int maxWidth = options.outWidth / 2;
        refreshCorner(SharePreferenceUtil.getSharePreferenceUtil(this).getValue("radius", 10f));
        float progress = (SharePreferenceUtil.getSharePreferenceUtil(this).getValue("radius", 10f) / maxWidth * 1000);
        ((AppCompatSeekBar) findViewById(R.id.round_corner_seek)).setProgress((int) progress);
        ((AppCompatSeekBar) findViewById(R.id.round_corner_seek)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                refreshCorner(maxWidth * (progress * 1f / 1000f));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharePreferenceUtil.getSharePreferenceUtil(MainActivity.this).putValue("radius", maxWidth * (seekBar.getProgress() * 1f / 1000f));
                Intent intent = new Intent();
                intent.setAction(WeixinWidget.WIDGET_BROADCAST);
                sendBroadcast(intent);
            }
        });
    }

    private void refreshCorner(float radius) {
        ((ImageView) findViewById(R.id.OspTabHostActivity)).setImageBitmap(BitmapUtil.getRoundCornerBitmap(this, R.drawable.alipay_fukuan, radius));
        ((ImageView) findViewById(R.id.PayeeQRActivity)).setImageBitmap(BitmapUtil.getRoundCornerBitmap(this, R.drawable.alipay_shoukuan, radius));
        ((ImageView) findViewById(R.id.MainCaptureActivity)).setImageBitmap(BitmapUtil.getRoundCornerBitmap(this, R.drawable.alipay_scan, radius));
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
