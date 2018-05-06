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

    public ColorSquare(Context context) {
        this(context, null);
    }

    public ColorSquare(Context context, AttributeSet attrs) {
        super(context, attrs);

        int side = (int) getResources().getDimension(R.dimen.color_square_side);
        int margin = (int) getResources().getDimension(R.dimen.color_square_margin);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(side, side);
        layoutParams.setMargins(margin, margin, margin, margin);

        setLayoutParams(layoutParams);

        setImageDrawable(getResources().getDrawable(R.drawable.borders_of_square));
    }
}
