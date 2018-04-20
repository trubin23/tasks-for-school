package ru.trubin23.tasksforschool.colorpicker;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Andrey on 19.04.2018.
 */

public class SliderView extends View {

    public SliderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        Path borderPath;
        Paint borderPaint;

        borderPath = new Path();
        borderPaint = Resources.makeLinePaint(context);
    }
}
