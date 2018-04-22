package ru.trubin23.tasksforschool.colorpicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.trubin23.tasksforschool.R;
import ru.trubin23.tasksforschool.util.ActivityUtils;

public class ColorPickerActivity extends AppCompatActivity {

    public static final int COLOR_SELECTION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colorpicker_act);

        ColorPickerFragment colorPickerFragment =
                (ColorPickerFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (colorPickerFragment == null) {
            colorPickerFragment = ColorPickerFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), colorPickerFragment, R.id.content_frame);
        }

        new ColorPickerPresenter(colorPickerFragment);
    }
}
