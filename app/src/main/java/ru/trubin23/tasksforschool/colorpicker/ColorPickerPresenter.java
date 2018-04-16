package ru.trubin23.tasksforschool.colorpicker;

import android.support.annotation.NonNull;

/**
 * Created by Andrey on 15.04.2018.
 */

public class ColorPickerPresenter implements ColorPickerContract.Presenter {

    private ColorPickerContract.View mView;

    ColorPickerPresenter(@NonNull ColorPickerContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
