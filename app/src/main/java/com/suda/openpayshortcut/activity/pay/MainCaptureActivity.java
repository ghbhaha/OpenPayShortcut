package com.suda.openpayshortcut.activity.pay;

import android.content.Intent;
import android.net.Uri;

/**
 * Created by guhaibo on 2017/10/13.
 */

public class MainCaptureActivity extends BasePayActivity {

    @Override
    public void openPay() {
        try {
            Uri uri = Uri.parse("alipays://platformapi/startapp?appId=10000007");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }catch (Exception e){
        }
    }
}
