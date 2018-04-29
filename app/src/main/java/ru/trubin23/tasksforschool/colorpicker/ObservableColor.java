package ru.trubin23.tasksforschool.colorpicker;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 22.04.2018.
 */

class ObservableColor {

    private final List<ColorObserver> mObservers = new ArrayList<>();

    private final float[] hsv = {0, 0, 0};

    ObservableColor(int color) {
        Color.colorToHSV(color, hsv);
    }

    void getHsv(float hsvOut[]) {
        hsvOut[0] = hsv[0];
        hsvOut[1] = hsv[1];
        hsvOut[2] = hsv[2];
    }

    public int getColor() {
        return Color.HSVToColor(hsv);
    }

    public float getHue() {
        return hsv[0];
    }

    public float getSat() {
        return hsv[1];
    }

    float getValue() {
        return hsv[2];
    }

    float getLightness() {
        return getLightnessWithValue(hsv[2]);
    }

    float getLightnessWithValue(float value) {
        float[] hsV = {hsv[0], hsv[1], value};
        final int color = Color.HSVToColor(hsV);
        return (Color.red(color) * 0.2126f + Color.green(color) * 0.7152f + Color.blue(color) * 0.0722f) / 0xff;
    }

    void updateValue(float value, ColorObserver sender) {
        hsv[2] = value;
        notifyOtherObservers(sender);
    }

    private void notifyOtherObservers(ColorObserver sender) {
        for (ColorObserver observer : mObservers) {
            if (observer != sender) {
                observer.updateColor(this);
            }
        }
    }

    void addObserver(ColorObserver observer) {
        mObservers.add(observer);
    }

    void updateHueSat(float hue, float sat, ColorObserver sender) {
        hsv[0] = hue;
        hsv[1] = sat;
        notifyOtherObservers(sender);
    }
}
