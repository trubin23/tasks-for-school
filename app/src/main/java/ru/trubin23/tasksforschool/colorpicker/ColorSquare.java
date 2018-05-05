package ru.trubin23.tasksforschool.colorpicker;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;

import ru.trubin23.tasksforschool.R;

/**
 * Created by Andrey on 03.05.2018.
 */

public class ColorSquare extends AppCompatImageView {

    public static int SQUARE_SIDE = 300;
    public static int SQUARE_MARGIN = 200;

    public ColorSquare(Context context) {
        super(context);

        setImageDrawable(getResources().getDrawable(R.drawable.borders_of_square));
    }
}
