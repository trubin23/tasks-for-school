package ru.trubin23.tasksforschool.tasks;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.trubin23.tasksforschool.data.Task;
import ru.trubin23.tasksforschool.taskdetail.TaskDetailActivity;

/**
 * Created by Andrey on 29.03.2018.
 */

public class TasksPresenter implements TasksContract.Presenter {

    private TasksContract.View mView;

    TasksPresenter(@NonNull TasksContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void activityResult(int requestCode, int resultCode, @Nullable Task task) {
        if (resultCode != Activity.RESULT_OK || task == null){
            return;
        }

        if (requestCode == TaskDetailActivity.CREATE_TASK){

        }

        if (requestCode == TaskDetailActivity.EDIT_TASK){

        }
    }
}
