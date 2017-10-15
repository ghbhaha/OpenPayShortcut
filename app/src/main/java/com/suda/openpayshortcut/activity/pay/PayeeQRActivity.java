package com.suda.openpayshortcut.activity.pay;

import android.content.Intent;
import android.net.Uri;

import com.suda.openpayshortcut.util.RootShell;

/**
 * Created by guhaibo on 2017/10/13.
 */

public class PayeeQRActivity extends BasePayActivity {

    @Override
    public void openPay() {
        try {
            Uri uri = Uri.parse("alipays://platformapi/startapp?appId=20000123");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }catch (Exception e){
        }
    }
}
