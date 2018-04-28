package ru.trubin23.tasksforschool.colorpicker;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Andrey on 26.04.2018.
 */

public class SquareView extends View {

    private static final int MIN_SIZE_DIP = 200;

    private final int mMinSizePx;

    public SquareView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mMinSizePx = (int) Resources.dipToPixels(context, MIN_SIZE_DIP);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int w = MeasureSpec.getSize(widthMeasureSpec);
        final int h = MeasureSpec.getSize(heightMeasureSpec);
        final int modeW = MeasureSpec.getMode(widthMeasureSpec);
        final int modeH = MeasureSpec.getMode(heightMeasureSpec);

        int size;
        if (modeW == MeasureSpec.UNSPECIFIED) {
            size = h;
        } else if (modeH == MeasureSpec.UNSPECIFIED) {
            size = w;
        } else {
            size = Math.min(w, h);
        }

        size = Math.max(size, mMinSizePx);
        setMeasuredDimension(size, size);
    }
}
