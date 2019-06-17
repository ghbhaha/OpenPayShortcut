package com.suda.openpayshortcut.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

/**
 * @author guhaibo
 * @date 2019/6/17
 */
public class BitmapUtil {

    public static Bitmap getRoundCornerBitmap(Context context, int id) {
        return getRoundCornerBitmap(context, id, SharePreferenceUtil.getSharePreferenceUtil(context).getValue("radius", 10f));
    }

    public static Bitmap getRoundCornerBitmap(Context context, int id, float radius) {
        Bitmap bitmapSrc = BitmapFactory.decodeResource(context.getResources(), id);
        Bitmap bitmapDest = Bitmap.createBitmap(bitmapSrc.getWidth(), bitmapSrc.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapDest);
        canvas.drawBitmap(bitmapSrc, new Matrix(), null);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(makeRoundRectBitmap(bitmapDest.getWidth(), bitmapDest.getHeight(), radius), new Matrix(), paint);
        return bitmapDest;
    }

    private static Bitmap makeRoundRectBitmap(int width, int height, float radius) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(new RectF(0, 0, width, height), radius, radius, paint);
        return bitmap;
    }
}
