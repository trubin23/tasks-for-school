package ru.trubin23.tasksforschool.tasks;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

/**
 * Created by Andrey on 29.03.2018.
 */

public class TasksFragment extends Fragment implements TasksContract.View {

    private TasksContract.Presenter mPresenter;

    @Override
    public void setPresenter(@NonNull TasksContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
