package ru.trubin23.tasksforschool.colorpicker;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.trubin23.tasksforschool.R;

/**
 * Created by Andrey on 15.04.2018.
 */

public class ColorPickerFragment extends Fragment implements ColorPickerContract.View {

    private static final int DEGREES_IN_CIRCLE = 360;

    private ColorPickerContract.Presenter mPresenter;

    private List<ColorSquare> mColorSquares;

    @BindView(R.id.color_picker)
    ConstraintLayout mConstraintLayout;

    public static ColorPickerFragment newInstance() {
        return new ColorPickerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.colorpicker_frag, container, false);
        ButterKnife.bind(this, root);

        int[] colors = buildHueColorArray();
        GradientDrawable gradientDrawable = new GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                colors);
        mConstraintLayout.setBackground(gradientDrawable);

        addColorSquares();

        return root;
    }

    private int[] buildHueColorArray() {
        int[] hue = new int[DEGREES_IN_CIRCLE];
        for (int i = 0; i < hue.length; i++) {
            hue[i] = Color.HSVToColor(new float[]{i, 1f, 1f});
        }
        return hue;
    }

    private void addColorSquares() {
        final int NUMBER_OF_SQUARES = 16;

        for (int i = 0; i < NUMBER_OF_SQUARES; i++) {
            ColorSquare colorSquare = new ColorSquare(getContext());
            colorSquare.setBackgroundColor(Color.HSVToColor(new float[]{
                    DEGREES_IN_CIRCLE*(i*2+1)/(NUMBER_OF_SQUARES*2), 1f, 1f}));
            mConstraintLayout.addView(colorSquare);
        }
    }

    @Override
    public void setPresenter(@NonNull ColorPickerContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
