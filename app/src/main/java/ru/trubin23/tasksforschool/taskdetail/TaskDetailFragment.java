package ru.trubin23.tasksforschool.taskdetail;

import android.app.Activity;
import android.content.Intent;
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
import ru.trubin23.tasksforschool.data.Task;

/**
 * Created by Andrey on 30.03.2018.
 */

public class TaskDetailFragment extends Fragment implements TaskDetailContract.View {

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
        fab.setOnClickListener(view -> mPresenter.saveTask(
                mTitle.getText().toString(), mDescription.getText().toString()));

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
                colorPicker();
                return true;
        }
        return false;
    }

    private void colorPicker(){
        Intent intent = new Intent(getContext(), ColorPickerActivity.class);
        startActivityForResult(intent, ColorPickerActivity.COLOR_SELECTION);
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
