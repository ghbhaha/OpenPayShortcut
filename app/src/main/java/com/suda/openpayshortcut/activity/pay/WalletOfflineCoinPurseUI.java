package com.suda.openpayshortcut.activity.pay;

import com.suda.openpayshortcut.util.RootShell;

/**
 * Created by guhaibo on 2017/10/13.
 */

public class WalletOfflineCoinPurseUI extends BasePayActivity {
    @Override
    public void openPay() {
        RootShell rootShell = RootShell.open();
        rootShell.execute("am start -n com.tencent.mm/com.tencent.mm.plugin.offline.ui.WalletOfflineCoinPurseUI");
        rootShell.close();
    }
}
