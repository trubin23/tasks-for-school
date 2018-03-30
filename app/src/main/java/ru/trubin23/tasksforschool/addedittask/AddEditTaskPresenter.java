package ru.trubin23.tasksforschool.addedittask;

import android.support.annotation.NonNull;

/**
 * Created by Andrey on 30.03.2018.
 */

public class AddEditTaskPresenter implements AddEditTaskContract.Presenter {

    private AddEditTaskContract.View mView;

    public AddEditTaskPresenter(@NonNull AddEditTaskContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
    }
}
