package com.suda.openpayshortcut.activity.pay;

import com.suda.openpayshortcut.util.RootShell;

/**
 * Created by guhaibo on 2017/10/13.
 */

public class PayeeQRActivity extends BasePayActivity {

    @Override
    public void openPay() {
        RootShell rootShell = RootShell.open();
        rootShell.execute("am start -n com.eg.android.AlipayGphone/com.alipay.mobile.payee.ui.PayeeQRActivity");
        rootShell.close();
    }
}
