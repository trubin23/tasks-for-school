package ru.trubin23.tasksforschool.addedittask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.trubin23.tasksforschool.R;
import ru.trubin23.tasksforschool.util.ActivityUtils;

public class AddEditTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addedittask_act);

        AddEditTaskFragment fragment = (AddEditTaskFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);
        if (fragment == null) {
            fragment = new AddEditTaskFragment();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), fragment, R.id.content_frame);
        }

        new AddEditTaskPresenter(fragment);
    }
}
