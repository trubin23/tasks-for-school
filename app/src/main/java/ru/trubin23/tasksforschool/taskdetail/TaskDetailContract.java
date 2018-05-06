package ru.trubin23.tasksforschool.taskdetail;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.trubin23.tasksforschool.BasePresenter;
import ru.trubin23.tasksforschool.BaseView;
import ru.trubin23.tasksforschool.data.Task;

/**
 * Created by Andrey on 30.03.2018.
 */

public interface TaskDetailContract {

    interface View extends BaseView<Presenter> {
        void setTitle(@NonNull String title);

        void setDescription(@NonNull String description);

        void showTaskList(@NonNull Task task);

        void showTitleError();

        void setColor(int color);
    }

    interface Presenter extends BasePresenter {

        void saveTask(@NonNull String title, @NonNull String description, int color);

        void activityResult(int requestCode, int resultCode, int color);
    }
}
