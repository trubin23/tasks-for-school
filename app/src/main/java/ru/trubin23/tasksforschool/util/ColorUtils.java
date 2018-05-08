package ru.trubin23.tasksforschool.util;

import android.graphics.Color;

/**
 * Created by Andrey on 08.05.2018.
 */

public class ColorUtils {

    public static int colorText(int backgroundColor) {
        int red = Color.red(backgroundColor);
        int green = Color.green(backgroundColor);
        int blue = Color.blue(backgroundColor);
        int y = (int) (0.2126 * red + 0.7152 * green + 0.0722 * blue);
        if (y < 128) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }

}
