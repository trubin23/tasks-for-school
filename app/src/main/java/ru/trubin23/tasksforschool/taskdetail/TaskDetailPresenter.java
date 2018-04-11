package ru.trubin23.tasksforschool.taskdetail;

import android.support.annotation.NonNull;

import ru.trubin23.tasksforschool.data.Task;

/**
 * Created by Andrey on 30.03.2018.
 */

public class TaskDetailPresenter implements TaskDetailContract.Presenter {

    private Task mTask;
    private TaskDetailContract.View mView;

    TaskDetailPresenter(@NonNull Task task, @NonNull TaskDetailContract.View view) {
        mTask = task;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.setTitle(mTask.getTitle());
        mView.setDescription(mTask.getDescription());
    }

    @Override
    public void saveTask(@NonNull String title, @NonNull String description) {
        if (!Task.isValidTitle(title)){
            mView.showTitleError();
        }

        if (mTask == null){
            Task task = new Task(title, description);
            mView.showTaskList(task);
        } else {
            mTask.setTitle(title);
            mTask.setDescription(description);
            mView.showTaskList(mTask);
        }
    }
}
