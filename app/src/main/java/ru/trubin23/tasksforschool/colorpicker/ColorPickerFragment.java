package ru.trubin23.tasksforschool.colorpicker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import ru.trubin23.tasksforschool.R;

/**
 * Created by Andrey on 15.04.2018.
 */

public class ColorPickerFragment extends Fragment implements ColorPickerContract.View {

    private ColorPickerContract.Presenter mPresenter;

    public static ColorPickerFragment newInstance() {
        return new ColorPickerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.colorpicker_frag, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void setPresenter(@NonNull ColorPickerContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
