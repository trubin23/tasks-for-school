package ru.trubin23.tasksforschool.tasks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.trubin23.tasksforschool.R;
import ru.trubin23.tasksforschool.util.ActivityUtils;

public class TasksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_act);

        TasksFragment tasksFragment =
                (TasksFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (tasksFragment == null){
            tasksFragment = new TasksFragment();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), tasksFragment, R.id.content_frame);
        }

        new TasksPresenter(tasksFragment);
    }
}
