package ru.trubin23.tasksforschool.colorpicker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.trubin23.tasksforschool.R;

/**
 * Created by Andrey on 15.04.2018.
 */

public class ColorPickerFragment extends Fragment implements ColorPickerContract.View {

    public static final String TASK_COLOR = "task_color";
    private static final String LAST_SELECTED_COLOR = "last_selected_color";

    private static final int DEGREES_IN_CIRCLE = 360;
    private static final int NUMBER_OF_SQUARES = 16;

    private ColorPickerContract.Presenter mPresenter;

    @BindView(R.id.color_picker)
    LinearLayout mColorPickerLayout;

    @BindView(R.id.selected_color)
    ImageView mSelectedColor;

    @BindView(R.id.description_color)
    TextView mDescriptionColor;

    public static ColorPickerFragment newInstance(int color) {
        ColorPickerFragment colorPickerFragment = new ColorPickerFragment();

        Bundle args = new Bundle();
        args.putInt(TASK_COLOR, color);
        colorPickerFragment.setArguments(args);

        return colorPickerFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.colorpicker_frag, container, false);
        ButterKnife.bind(this, root);

        setHasOptionsMenu(true);

        int[] colors = buildHueColorArray();
        GradientDrawable gradientDrawable = new GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                colors);
        mColorPickerLayout.setBackground(gradientDrawable);

        addColorSquares();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int color = colorCalculation(0, NUMBER_OF_SQUARES);

        Bundle bundle = getArguments();
        if (bundle != null) {
            color = bundle.getInt(TASK_COLOR, color);
        }

        if (savedInstanceState != null) {
            color = savedInstanceState.getInt(LAST_SELECTED_COLOR, color);
        }
        colorSelection(color);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        ColorDrawable drawable = (ColorDrawable) mSelectedColor.getBackground();
        outState.putInt(LAST_SELECTED_COLOR, drawable.getColor());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.colorpicker_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_done:
                Intent intent = new Intent();

                ColorDrawable drawable = (ColorDrawable) mSelectedColor.getBackground();
                intent.putExtra(TASK_COLOR, drawable.getColor());

                getActivity().setResult(Activity.RESULT_OK, intent);
                getActivity().finish();
                return true;
        }
        return false;
    }

    private int[] buildHueColorArray() {
        int[] hue = new int[DEGREES_IN_CIRCLE];
        for (int i = 0; i < hue.length; i++) {
            hue[i] = Color.HSVToColor(new float[]{i, 1f, 1f});
        }
        return hue;
    }

    private int colorCalculation(int index, int numberOfSquares) {
        float centerSquare = DEGREES_IN_CIRCLE * (index * 2 + 1) / (numberOfSquares * 2.0f);
        return Color.HSVToColor(new float[]{centerSquare, 1f, 1f});
    }

    private void addColorSquares() {
        for (int i = 0; i < NUMBER_OF_SQUARES; i++) {
            ColorSquare colorSquare = new ColorSquare(getContext());
            if (i == 0) {
                colorSquare.callOnClick();
            }

            int color = colorCalculation(i, NUMBER_OF_SQUARES);
            colorSquare.setBackgroundColor(color);
            mColorPickerLayout.addView(colorSquare);

            colorSquare.setOnClickListener(view -> {
                ColorDrawable drawable = (ColorDrawable) view.getBackground();
                colorSelection(drawable.getColor());
            });
        }
    }

    private void colorSelection(int color) {
        mSelectedColor.setBackgroundColor(color);

        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);

        float[] hsv = new float[3];
        Color.RGBToHSV(red, green, blue, hsv);

        @SuppressLint("DefaultLocale")
        String descriptionText = String.format("HSV:  %.2f ; %.2f ; %.2f\n\n" +
                        "RGB:  %d ; %d ; %d",
                hsv[0], hsv[1], hsv[2],
                red, green, blue);

        mDescriptionColor.setText(descriptionText);
    }

    @Override
    public void setPresenter(@NonNull ColorPickerContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
