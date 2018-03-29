package ru.trubin23.tasksforschool.tasks;

import android.support.annotation.NonNull;

/**
 * Created by Andrey on 29.03.2018.
 */

public class TasksPresenter implements TasksContract.Presenter {

    private TasksContract.View mView;

    public TasksPresenter(@NonNull TasksContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
