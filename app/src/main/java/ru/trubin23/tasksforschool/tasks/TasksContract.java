package ru.trubin23.tasksforschool.tasks;

import ru.trubin23.tasksforschool.BasePresenter;
import ru.trubin23.tasksforschool.BaseView;

/**
 * Created by Andrey on 29.03.2018.
 */

public interface TasksContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

        void activityResult(int requestCode, int resultCode);
    }
}
