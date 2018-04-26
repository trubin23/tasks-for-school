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

public class SwatchView extends SquareView implements ColorObserver {

    private Path mBorderPath;
    private Paint mBorderPaint;

    public SwatchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mBorderPaint = Resources.makeLinePaint(context);

        mBorderPath = new Path();
    }

    @Override
    public void updateColor(ObservableColor observableColor) {

    }
}
