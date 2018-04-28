package ru.trubin23.tasksforschool.colorpicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import ru.trubin23.tasksforschool.R;

/**
 * Created by Andrey on 20.04.2018.
 */

class Resources {

    private static final float LINE_WIDTH_DIP = 1.5f;
    private static final float POINTER_RADIUS_DIP = 7;
    private static final int VIEW_OUTLINE_COLOR = 0xff808080;

    static Paint makeLinePaint(Context context){
        Paint paint = new Paint();
        paint.setColor(VIEW_OUTLINE_COLOR);
        paint.setStrokeWidth(dipToPixels(context, LINE_WIDTH_DIP));
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        return paint;
    }

    static float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

    static Paint makeCheckerPaint(Context context) {
        Paint paint = new Paint();
        final Bitmap checkerBmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.checker_background);
        paint.setShader(new BitmapShader(checkerBmp, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
        paint.setStrokeWidth(dipToPixels(context, LINE_WIDTH_DIP));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        return paint;
    }

    static Path makePointerPath(Context context) {
        Path pointerPath = new Path();
        final float radiusPx = dipToPixels(context, POINTER_RADIUS_DIP);
        pointerPath.addCircle(0, 0, radiusPx, Path.Direction.CW);
        return pointerPath;
    }
}
