package com.suda.openpayshortcut.activity.pay;

import android.content.Intent;

import com.jaredrummler.android.shell.CommandResult;
import com.jaredrummler.android.shell.Shell;
import com.suda.openpayshortcut.service.OpenPayService;

/**
 * Created by guhaibo on 2017/10/13.
 */

public class WalletOfflineCoinPurseUI extends BasePayActivity {
    @Override
    public void openPay() {
        CommandResult result = Shell.SU.run("am start -n com.tencent.mm/com.tencent.mm.plugin.offline.ui.WalletOfflineCoinPurseUI");
        if (!result.isSuccessful()) {
            if (getAccessibility()) {
                OpenPayService.initForPay();
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.tencent.mm");
                if (intent != null) {
                    startActivity(intent);
                }
            }
        }
    }
}
