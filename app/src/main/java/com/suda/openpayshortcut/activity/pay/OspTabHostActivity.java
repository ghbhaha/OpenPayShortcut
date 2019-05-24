package com.suda.openpayshortcut.activity.pay;

import android.content.Intent;
import android.net.Uri;

/**
 * Created by guhaibo on 2017/10/13.
 */

public class OspTabHostActivity extends BasePayActivity {

    @Override
    public void openPay() {
//        RootShell rootShell = RootShell.open();
//        rootShell.execute("am start -n com.eg.android.AlipayGphone/com.alipay.mobile.onsitepay9.payer.OspTabHostActivity");
//        rootShell.close();
        try {
            Uri uri = Uri.parse("alipays://platformapi/startapp?appId=20000056");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }catch (Exception e){
        }
    }
}
