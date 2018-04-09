package ru.trubin23.tasksforschool.taskdetail;

import android.support.annotation.NonNull;
import android.text.Editable;

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
    }

    interface Presenter extends BasePresenter {

        void saveTask(@NonNull String text, @NonNull String text1);
    }
}
