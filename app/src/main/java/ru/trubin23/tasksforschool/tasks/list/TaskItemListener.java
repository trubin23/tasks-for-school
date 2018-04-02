package ru.trubin23.tasksforschool.tasks.list;

import android.support.annotation.NonNull;

import ru.trubin23.tasksforschool.data.Task;

/**
 * Created by Andrey on 02.04.2018.
 */

interface TaskItemListener {

    void onTaskClick(@NonNull Task task);
}
