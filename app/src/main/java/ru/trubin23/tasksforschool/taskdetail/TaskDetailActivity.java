package ru.trubin23.tasksforschool.taskdetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.trubin23.tasksforschool.R;
import ru.trubin23.tasksforschool.util.ActivityUtils;

public class TaskDetailActivity extends AppCompatActivity {

    public static final String TASK_DETAILS = "TASK_DETAILS";
    public static final int CHANGE_TASK = 2;
    public static final int CREATE_TASK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskdetail_act);

        TaskDetailFragment fragment = (TaskDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);
        if (fragment == null) {
            fragment = new TaskDetailFragment();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), fragment, R.id.content_frame);
        }

        new TaskDetailPresenter(fragment);
    }
}
