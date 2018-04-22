package ru.trubin23.tasksforschool.colorpicker;

import android.graphics.Color;

/**
 * Created by Andrey on 22.04.2018.
 */

class ObservableColor {

    private final float[] hsv = {0, 0, 0};

    ObservableColor(int color) {
        Color.colorToHSV(color, hsv);
    }

    void getHsv(float hsvOut[]) {
        hsvOut[0] = hsv[0];
        hsvOut[1] = hsv[1];
        hsvOut[2] = hsv[2];
    }
}
