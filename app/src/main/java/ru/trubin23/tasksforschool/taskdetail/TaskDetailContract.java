package ru.trubin23.tasksforschool.taskdetail;

import android.support.annotation.NonNull;

import ru.trubin23.tasksforschool.BasePresenter;
import ru.trubin23.tasksforschool.BaseView;

/**
 * Created by Andrey on 30.03.2018.
 */

public interface TaskDetailContract {

    interface View extends BaseView<Presenter> {
        void setTitle(@NonNull String title);

        void setDescription(@NonNull String description);
    }

    interface Presenter extends BasePresenter {

    }
}
