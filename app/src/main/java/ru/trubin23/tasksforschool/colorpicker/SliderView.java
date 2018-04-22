package ru.trubin23.tasksforschool.colorpicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Andrey on 19.04.2018.
 */

public class SliderView extends View {

    private ObservableColor mObservableColor = new ObservableColor(0);

    Path borderPath;
    Paint borderPaint;

    public SliderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        borderPath = new Path();
        borderPaint = Resources.makeLinePaint(context);
    }

    protected Bitmap makeBitmap(int w, int h) {
        final boolean isWide = w > h;
        final int n = Math.max(w, h);
        int[] colors = new int[n];

        float[] hsv = new float[]{0, 0, 0};
        mObservableColor.getHsv(hsv);

        for (int i = 0; i < n; ++i) {
            hsv[2] = isWide ? (float)i / n : 1 - (float)i / n;
            colors[i] = Color.HSVToColor(hsv);
        }
        final int bmpWidth = isWide ? w : 1;
        final int bmpHeight = isWide ? 1 : h;
        return Bitmap.createBitmap(colors, bmpWidth, bmpHeight, Bitmap.Config.ARGB_8888);
    }
}
