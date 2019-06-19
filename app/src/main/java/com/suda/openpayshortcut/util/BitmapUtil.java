package com.suda.openpayshortcut.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
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
        Path path = new Path();
        RectF rectF = new RectF(0, 0, bitmapDest.getWidth(), bitmapDest.getHeight());
        path.addRoundRect(rectF,radius,radius, Path.Direction.CW);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmapSrc, new Matrix(), paint);
        return bitmapDest;
    }

}
