package ru.trubin23.tasksforschool.taskdetail;

import android.support.annotation.NonNull;

/**
 * Created by Andrey on 30.03.2018.
 */

public class TaskDetailPresenter implements TaskDetailContract.Presenter {

    private TaskDetailContract.View mView;

    public TaskDetailPresenter(@NonNull TaskDetailContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
    }
}
