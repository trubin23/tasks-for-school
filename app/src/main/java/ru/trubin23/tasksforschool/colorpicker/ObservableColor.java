package ru.trubin23.tasksforschool.colorpicker;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 22.04.2018.
 */

class ObservableColor {

    private final List<ColorObserver> mObservers = new ArrayList<>();

    private final float[] mHsv = {0, 0, 0};

    ObservableColor(int color) {
        Color.colorToHSV(color, mHsv);
    }

    void getHsv(float hsvOut[]) {
        hsvOut[0] = mHsv[0];
        hsvOut[1] = mHsv[1];
        hsvOut[2] = mHsv[2];
    }

    public int getColor() {
        return Color.HSVToColor(mHsv);
    }

    float getHue() {
        return mHsv[0];
    }

    float getSat() {
        return mHsv[1];
    }

    float getValue() {
        return mHsv[2];
    }

    float getLightness() {
        return getLightnessWithValue(mHsv[2]);
    }

    float getLightnessWithValue(float value) {
        float[] hsV = {mHsv[0], mHsv[1], value};
        final int color = Color.HSVToColor(hsV);
        return (Color.red(color) * 0.2126f + Color.green(color) * 0.7152f + Color.blue(color) * 0.0722f) / 0xff;
    }

    void updateValue(float value, ColorObserver sender) {
        mHsv[2] = value;
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
        mHsv[0] = hue;
        mHsv[1] = sat;
        notifyOtherObservers(sender);
    }

    void updateColor(int color, ColorObserver sender) {
        Color.colorToHSV(color, mHsv);
        notifyOtherObservers(sender);
    }
}
