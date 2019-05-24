package com.suda.openpayshortcut.service;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * @author guhaibo
 * @date 2019/5/24
 */
public class OpenPayService extends AccessibilityService {


    public static boolean isOpenPop = false;
    public static boolean isOpenScan = false;
    public static boolean isOpenPay = false;
    public static boolean isOpenWallet = false;

    /**
     * 初始化扫一扫参数
     */
    public static void initForScan() {
        isOpenPop = false;
        isOpenScan = false;
        isOpenPay = true;
        isOpenWallet = true;
    }

    /**
     * 初始化付款码参数
     */
    public static void initForPay() {
        isOpenPop = false;
        isOpenScan = true;
        isOpenPay = false;
        isOpenWallet = true;
    }

    /**
     * 初始化收款码参数
     */
    public static void initForWallet() {
        isOpenPop = false;
        isOpenScan = true;
        isOpenPay = false;
        isOpenWallet = false;
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            AccessibilityNodeInfo root = getRootInActiveWindow();
            //第一步打开popwindow
            if (!isOpenPop) {
                List<AccessibilityNodeInfo> jbs = root.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/jb");
                for (AccessibilityNodeInfo jb : jbs) {
                    AccessibilityNodeInfo parent = jb.getParent();
                    if (parent != null) {
                        if ("更多功能按钮".equals(parent.getContentDescription()) && parent.isClickable()) {
                            parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            isOpenPop = true;
                        }
                        parent.recycle();
                    }
                }
            } else {
                if (!isOpenScan) {
                    List<AccessibilityNodeInfo> jbs = root.findAccessibilityNodeInfosByText("扫一扫");
                    for (AccessibilityNodeInfo jb : jbs) {
                        if ("com.tencent.mm:id/cx".equals(jb.getViewIdResourceName())) {
                            if (doClick(jb)) {
                                isOpenScan = true;
                            }
                        }
                    }
                }

                if (!isOpenPay) {
                    List<AccessibilityNodeInfo> jbs = root.findAccessibilityNodeInfosByText("收付款");
                    for (AccessibilityNodeInfo jb : jbs) {
                        if ("com.tencent.mm:id/cx".equals(jb.getViewIdResourceName())) {
                            if (doClick(jb)) {
                                isOpenPay = true;
                            }
                        }
                    }
                }

                if (!isOpenWallet) {
                    List<AccessibilityNodeInfo> jbs = root.findAccessibilityNodeInfosByText("二维码收款");
                    for (AccessibilityNodeInfo jb : jbs) {
                        if (doClick(jb)) {
                            isOpenWallet = true;
                        }
                    }
                }
            }
        }
    }

    private boolean doClick(AccessibilityNodeInfo accessibilityNodeInfo) {
        boolean click = false;
        if (accessibilityNodeInfo.isClickable()) {
            accessibilityNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            click = true;
        } else {
            AccessibilityNodeInfo parent = accessibilityNodeInfo.getParent();
            if (parent != null) {
                if (parent.isClickable()) {
                    parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    click = true;
                }
                parent.recycle();
            }
        }
        return click;
    }

    @Override
    public void onInterrupt() {
        Log.d(getClass().getSimpleName(), "onInterrupt");
    }
}
