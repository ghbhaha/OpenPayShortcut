package com.suda.openpayshortcut.activity.pay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.suda.openpayshortcut.util.RootShell;

public class PayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (PayeeQRActivity.class == getClass()) {
            onStartBnGetmoneyClicked();
        } else if (OspTabHostActivity.class == getClass()) {
            onStartBnPayalipayClicked();
        } else if (MainCaptureActivity.class == getClass()) {
            onStartBnSaoyisaoalipayClicked();
        } else if (CollectMainUI.class == getClass()) {
            onStartBnWechatgetmoneyClicked();
        } else if (WalletOfflineCoinPurseUI.class == getClass()) {
            onStartBnWechatpayClicked();
        } else if (BaseScanUI.class == getClass()) {
            onStartBnWechatsaoyisaoClicked();
        }
        finish();
    }

    public void onStartBnGetmoneyClicked() {
        RootShell rootShell = RootShell.open();
        rootShell.execute("am start -n com.eg.android.AlipayGphone/com.alipay.mobile.payee.ui.PayeeQRActivity");
        rootShell.close();
    }

    public void onStartBnPayalipayClicked() {
        RootShell rootShell = RootShell.open();
        rootShell.execute("am start -n com.eg.android.AlipayGphone/com.alipay.mobile.onsitepay9.payer.OspTabHostActivity");
        rootShell.close();
    }

    public void onStartBnSaoyisaoalipayClicked() {
        RootShell rootShell = RootShell.open();
        rootShell.execute("am start -n com.eg.android.AlipayGphone/com.alipay.mobile.scan.as.main.MainCaptureActivity");
        rootShell.close();
    }

    public void onStartBnWechatgetmoneyClicked() {
        RootShell rootShell = RootShell.open();
        rootShell.execute("am start -n com.tencent.mm/com.tencent.mm.plugin.collect.ui.CollectMainUI");
        rootShell.close();
    }

    public void onStartBnWechatpayClicked() {
        RootShell rootShell = RootShell.open();
        rootShell.execute("am start -n com.tencent.mm/com.tencent.mm.plugin.offline.ui.WalletOfflineCoinPurseUI");
        rootShell.close();
    }

    public void onStartBnWechatsaoyisaoClicked() {
        RootShell rootShell = RootShell.open();
        rootShell.execute("am start -n com.tencent.mm/com.tencent.mm.plugin.scanner.ui.BaseScanUI");
        rootShell.close();
    }

}
