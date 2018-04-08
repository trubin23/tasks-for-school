package ru.trubin23.tasksforschool.tasks;

import android.support.annotation.Nullable;

import ru.trubin23.tasksforschool.BasePresenter;
import ru.trubin23.tasksforschool.BaseView;
import ru.trubin23.tasksforschool.data.Task;

/**
 * Created by Andrey on 29.03.2018.
 */

public interface TasksContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

        void activityResult(int requestCode, int resultCode, @Nullable Task task);
    }
}
