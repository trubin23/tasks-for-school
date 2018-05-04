package ru.trubin23.tasksforschool.colorpicker;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by Andrey on 03.05.2018.
 */

public class ColorSquare extends AppCompatImageView {

    private static int SQUARE_SIDE = 200;
    private static int SQUARE_MARGIN = 50;

    public ColorSquare(Context context) {
        super(context);
        setMinimumWidth(SQUARE_SIDE);
        setMinimumHeight(SQUARE_SIDE);
    }

    public ColorSquare(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
