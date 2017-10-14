package com.suda.openpayshortcut.service;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.suda.openpayshortcut.R;
import com.suda.openpayshortcut.activity.pay.BaseScanUI;
import com.suda.openpayshortcut.activity.pay.CollectMainUI;
import com.suda.openpayshortcut.activity.pay.MainCaptureActivity;
import com.suda.openpayshortcut.activity.pay.OspTabHostActivity;
import com.suda.openpayshortcut.activity.pay.PayeeQRActivity;
import com.suda.openpayshortcut.activity.pay.WalletOfflineCoinPurseUI;

/**
 * Created by guhaibo on 2017/10/13.
 */

public class AllWidget extends AppWidgetProvider {

    //定义我们要发送的事件
    public static final String WIDGET_BROADCAST = "WIDGET_BROADCAST";

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }


    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Intent intent = new Intent();
        intent.setAction(AllWidget.WIDGET_BROADCAST);
        context.sendBroadcast(intent);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Intent intent = new Intent();
        intent.setAction(AllWidget.WIDGET_BROADCAST);
        context.sendBroadcast(intent);
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (intent.getAction().equals(WIDGET_BROADCAST)) {
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.all_widget_layout);

            Intent intentNew = new Intent(context, PayeeQRActivity.class);
            PendingIntent pending = PendingIntent.getActivity(context, 0, intentNew, PendingIntent.FLAG_UPDATE_CURRENT);
            rv.setOnClickPendingIntent(R.id.PayeeQRActivity, pending);

            intentNew = new Intent(context, OspTabHostActivity.class);
            pending = PendingIntent.getActivity(context, 0, intentNew, PendingIntent.FLAG_UPDATE_CURRENT);
            rv.setOnClickPendingIntent(R.id.OspTabHostActivity, pending);

            intentNew = new Intent(context, MainCaptureActivity.class);
            pending = PendingIntent.getActivity(context, 0, intentNew, PendingIntent.FLAG_UPDATE_CURRENT);
            rv.setOnClickPendingIntent(R.id.MainCaptureActivity, pending);

            intentNew = new Intent(context, CollectMainUI.class);
            pending = PendingIntent.getActivity(context, 0, intentNew, PendingIntent.FLAG_UPDATE_CURRENT);
            rv.setOnClickPendingIntent(R.id.CollectMainUI, pending);

            intentNew = new Intent(context, WalletOfflineCoinPurseUI.class);
            pending = PendingIntent.getActivity(context, 0, intentNew, PendingIntent.FLAG_UPDATE_CURRENT);
            rv.setOnClickPendingIntent(R.id.WalletOfflineCoinPurseUI, pending);

            intentNew = new Intent(context, BaseScanUI.class);
            pending = PendingIntent.getActivity(context, 0, intentNew, PendingIntent.FLAG_UPDATE_CURRENT);
            rv.setOnClickPendingIntent(R.id.BaseScanUI, pending);

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName componentName = new ComponentName(context, AllWidget.class);
            appWidgetManager.updateAppWidget(componentName, rv);
        }
        super.onReceive(context, intent);
    }
}
