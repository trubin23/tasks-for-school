package ru.trubin23.tasksforschool.taskdetail;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.trubin23.tasksforschool.colorpicker.ColorPickerActivity;
import ru.trubin23.tasksforschool.data.Task;

/**
 * Created by Andrey on 30.03.2018.
 */

public class TaskDetailPresenter implements TaskDetailContract.Presenter {

    private Task mTask;
    private TaskDetailContract.View mView;

    TaskDetailPresenter(@NonNull TaskDetailContract.View view, @Nullable Task task) {
        mTask = task;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        if (mTask != null) {
            mView.setTitle(mTask.getTitle());
            mView.setDescription(mTask.getDescription());
            mView.setColor(mTask.getColor());
        }
    }

    @Override
    public void saveTask(@NonNull String title, @NonNull String description, int color) {
        if (!Task.isValidTitle(title)) {
            mView.showTitleError();
            return;
        }

        if (mTask == null) {
            Task task = new Task(title, description, color);
            mView.showTaskList(task);
        } else {
            mTask.setTitle(title);
            mTask.setDescription(description);
            mTask.setColor(color);
            mView.showTaskList(mTask);
        }
    }

    @Override
    public void activityResult(int requestCode, int resultCode, int color) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == ColorPickerActivity.COLOR_SELECTION) {
            mView.setColor(color);
        }
    }
}
