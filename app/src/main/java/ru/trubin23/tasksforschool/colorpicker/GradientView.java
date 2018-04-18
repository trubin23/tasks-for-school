package ru.trubin23.tasksforschool.colorpicker;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

/**
 * Created by Andrey on 18.04.2018.
 */

public class GradientView extends View {

    private static final int[] GRAD_COLORS = new int[]{Color.RED, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA, Color.RED};
    private static final int[] GRAD_ALPHA = new int[]{Color.WHITE, Color.TRANSPARENT};

    public GradientView(Context context) {
        super(context);
    }
}
