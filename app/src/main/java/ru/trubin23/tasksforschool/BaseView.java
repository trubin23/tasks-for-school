package ru.trubin23.tasksforschool;

import android.support.annotation.NonNull;

/**
 * Created by Andrey on 28.03.2018.
 */

public interface BaseView<T> {

    void setPresenter(@NonNull T presenter);
}
