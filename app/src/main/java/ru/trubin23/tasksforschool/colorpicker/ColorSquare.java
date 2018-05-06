package ru.trubin23.tasksforschool.colorpicker;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.widget.LinearLayout;

import ru.trubin23.tasksforschool.R;

/**
 * Created by Andrey on 03.05.2018.
 */

public class ColorSquare extends AppCompatImageView {

    public static int SQUARE_SIDE = 200;
    public static int SQUARE_MARGIN = 50;

    public ColorSquare(Context context) {
        super(context);

        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(SQUARE_SIDE, SQUARE_SIDE);
        layoutParams.setMargins(SQUARE_MARGIN, SQUARE_MARGIN, SQUARE_MARGIN, SQUARE_MARGIN);

        setLayoutParams(layoutParams);

        setImageDrawable(getResources().getDrawable(R.drawable.borders_of_square));
    }
}
