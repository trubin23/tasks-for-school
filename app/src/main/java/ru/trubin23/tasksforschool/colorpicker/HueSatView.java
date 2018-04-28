package ru.trubin23.tasksforschool.colorpicker;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Andrey on 19.04.2018.
 */

public class HueSatView extends SquareView implements ColorObserver {
    public HueSatView(Context context) {
        this(context, null);
    }

    public HueSatView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void updateColor(ObservableColor observableColor) {

    }
}
