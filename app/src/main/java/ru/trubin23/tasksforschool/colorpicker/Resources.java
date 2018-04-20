package ru.trubin23.tasksforschool.colorpicker;

import android.content.Context;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by Andrey on 20.04.2018.
 */

public class Resources {

    private static final float LINE_WIDTH_DIP = 1.5f;
    private static final int VIEW_OUTLINE_COLOR = 0xff808080;

    static Paint makeLinePaint(Context context){
        Paint paint = new Paint();
        paint.setColor(VIEW_OUTLINE_COLOR);
        paint.setStrokeWidth(dipToPixels(context, LINE_WIDTH_DIP));
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        return paint;
    }

    public static float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }
}
