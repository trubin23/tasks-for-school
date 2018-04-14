package ru.trubin23.tasksforschool.taskdetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.trubin23.tasksforschool.R;
import ru.trubin23.tasksforschool.data.Task;
import ru.trubin23.tasksforschool.util.ActivityUtils;

public class TaskDetailActivity extends AppCompatActivity {

    public static final String TASK_DETAILS = "TASK_DETAILS";
    public static final String TASK_SAVE = "TASK_SAVE";
    public static final int CREATE_TASK = 1;
    public static final int EDIT_TASK = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskdetail_act);

        TaskDetailFragment fragment = (TaskDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);
        if (fragment == null) {
            fragment = TaskDetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), fragment, R.id.content_frame);
        }

        Task task = getIntent().getParcelableExtra(TASK_DETAILS);

        new TaskDetailPresenter(task, fragment);
    }
}
