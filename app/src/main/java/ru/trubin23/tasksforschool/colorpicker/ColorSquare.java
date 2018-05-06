package ru.trubin23.tasksforschool.colorpicker;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import ru.trubin23.tasksforschool.R;

/**
 * Created by Andrey on 03.05.2018.
 */

public class ColorSquare extends AppCompatImageView {

    private static int SQUARE_SIDE = 200;
    private static int SQUARE_MARGIN = 50;

    public ColorSquare(Context context) {
        this(context, null);
    }

    public ColorSquare(Context context, AttributeSet attrs) {
        super(context, attrs);

        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(SQUARE_SIDE, SQUARE_SIDE);
        layoutParams.setMargins(SQUARE_MARGIN, SQUARE_MARGIN, SQUARE_MARGIN, SQUARE_MARGIN);

        setLayoutParams(layoutParams);

        setImageDrawable(getResources().getDrawable(R.drawable.borders_of_square));
    }
}
