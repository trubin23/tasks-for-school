package ru.trubin23.tasksforschool.colorpicker;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.trubin23.tasksforschool.R;

/**
 * Created by Andrey on 15.04.2018.
 */

public class ColorPickerFragment extends Fragment implements ColorPickerContract.View {

    private static final int DEGREES_IN_CIRCLE = 360;

    private ColorPickerContract.Presenter mPresenter;

    @BindView(R.id.color_picker)
    LinearLayout mColorPickerLayout;

    @BindView(R.id.selected_color)
    ImageView mSelectedColor;

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
        mColorPickerLayout.setBackground(gradientDrawable);

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

    private int colorCalculation(int index, int numberOfSquares) {
        float centerSquare = DEGREES_IN_CIRCLE * (index * 2 + 1) / (numberOfSquares * 2);
        return Color.HSVToColor(new float[]{centerSquare, 1f, 1f});
    }

    private void addColorSquares() {
        final int NUMBER_OF_SQUARES = 16;

        for (int i = 0; i < NUMBER_OF_SQUARES; i++) {
            ColorSquare colorSquare = new ColorSquare(getContext());

            int color = colorCalculation(i, NUMBER_OF_SQUARES);
            colorSquare.setBackgroundColor(color);
            mColorPickerLayout.addView(colorSquare);

            colorSquare.setOnClickListener(this::colorSelection);
        }

        int color = colorCalculation(0, NUMBER_OF_SQUARES);
        mSelectedColor.setBackgroundColor(color);
    }

    private void colorSelection(View view) {
        ColorDrawable drawable = (ColorDrawable) view.getBackground();
        mSelectedColor.setBackgroundColor(drawable.getColor());
    }

    @Override
    public void setPresenter(@NonNull ColorPickerContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
