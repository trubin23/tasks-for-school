package ru.trubin23.tasksforschool.taskdetail;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.trubin23.tasksforschool.R;
import ru.trubin23.tasksforschool.colorpicker.ColorPickerActivity;
import ru.trubin23.tasksforschool.colorpicker.ColorPickerFragment;
import ru.trubin23.tasksforschool.data.Task;
import ru.trubin23.tasksforschool.util.ColorUtils;

/**
 * Created by Andrey on 30.03.2018.
 */

public class TaskDetailFragment extends Fragment implements TaskDetailContract.View {

    private static final String SELECTED_COLOR = "selected_color";

    private TaskDetailContract.Presenter mPresenter;

    @BindView(R.id.task_title)
    EditText mTitle;

    @BindView(R.id.task_description)
    EditText mDescription;

    public static TaskDetailFragment newInstance() {
        return new TaskDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.taskdetail_frag, container, false);
        ButterKnife.bind(this, root);

        setHasOptionsMenu(true);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab_save_task);
        fab.setOnClickListener(view -> {
            int color = Color.WHITE;
            if (mTitle.getBackground() instanceof ColorDrawable) {
                ColorDrawable drawable = (ColorDrawable) mTitle.getBackground();
                color = drawable.getColor();
            }
            mPresenter.saveTask(mTitle.getText().toString(), mDescription.getText().toString(),
                    color);
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.taskdetail_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_color_picker:
                int color = Color.WHITE;
                if (mTitle.getBackground() instanceof ColorDrawable) {
                    ColorDrawable drawable = (ColorDrawable) mTitle.getBackground();
                    color = drawable.getColor();
                }

                Intent intent = new Intent(getContext(), ColorPickerActivity.class);
                intent.putExtra(ColorPickerFragment.TASK_COLOR, color);

                startActivityForResult(intent, ColorPickerActivity.COLOR_SELECTION);
                return true;
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int color = Color.WHITE;
        if (data != null) {
            color = data.getIntExtra(ColorPickerFragment.TASK_COLOR, color);
        }
        mPresenter.activityResult(requestCode, resultCode, color);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            int color = savedInstanceState.getInt(SELECTED_COLOR, Color.WHITE);
            setColor(color);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mTitle.getBackground() instanceof ColorDrawable) {
            ColorDrawable drawable = (ColorDrawable) mTitle.getBackground();
            outState.putInt(SELECTED_COLOR, drawable.getColor());
        }
    }

    @Override
    public void setPresenter(@NonNull TaskDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setTitle(@NonNull String title) {
        mTitle.setText(title);
    }

    @Override
    public void setDescription(@NonNull String description) {
        mDescription.setText(description);
    }

    @Override
    public void setColor(int color) {
        mTitle.setBackgroundColor(color);
        mDescription.setBackgroundColor(color);

        mTitle.setTextColor(ColorUtils.colorText(color));
        mDescription.setTextColor(ColorUtils.colorText(color));
    }

    @Override
    public void showTaskList(@NonNull Task task) {
        Intent intent = new Intent();

        intent.putExtra(TaskDetailActivity.TASK_SAVE, task);

        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }

    @Override
    public void showTitleError() {
        Snackbar.make(getView(), R.string.error_title_message, Snackbar.LENGTH_LONG).show();
    }
}
