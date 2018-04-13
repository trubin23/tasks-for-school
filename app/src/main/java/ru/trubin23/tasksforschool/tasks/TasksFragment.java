package ru.trubin23.tasksforschool.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.trubin23.tasksforschool.R;
import ru.trubin23.tasksforschool.taskdetail.TaskDetailActivity;
import ru.trubin23.tasksforschool.data.Task;
import ru.trubin23.tasksforschool.tasks.list.TaskItemListener;
import ru.trubin23.tasksforschool.tasks.list.TasksAdapter;

/**
 * Created by Andrey on 29.03.2018.
 */

public class TasksFragment extends Fragment implements TasksContract.View {

    private static final String TASKS_FOR_LIST = "TASKS_FOR_LIST";

    private TasksContract.Presenter mPresenter;

    @BindView(R.id.list_view)
    ListView mListView;

    private TasksAdapter mTasksAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tasks_frag, container, false);
        ButterKnife.bind(this, root);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab_add_task);
        fab.setOnClickListener(v -> addTask());

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<Task> tasks = null;
        if (savedInstanceState != null) {
            tasks = savedInstanceState.getParcelableArrayList(TASKS_FOR_LIST);
        }

        TaskItemListener taskItemListener = this::showTaskDetail;
        mTasksAdapter = new TasksAdapter(tasks, taskItemListener);

        mListView.setAdapter(mTasksAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList(TASKS_FOR_LIST, new ArrayList<>(mTasksAdapter.getTasks()));
    }

    @Override
    public void setPresenter(@NonNull TasksContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private void showTaskDetail(@NonNull Task task) {
        Intent intent = new Intent(getContext(), TaskDetailActivity.class);
        intent.putExtra(TaskDetailActivity.TASK_DETAILS, task);
        startActivityForResult(intent, TaskDetailActivity.EDIT_TASK);
    }

    private void addTask() {
        Intent intent = new Intent(getContext(), TaskDetailActivity.class);
        startActivityForResult(intent, TaskDetailActivity.CREATE_TASK);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Task task = null;
        if (data != null) {
            task = data.getParcelableExtra(TaskDetailActivity.TASK_SAVE);
        }
        mPresenter.activityResult(requestCode, resultCode, task);
    }

    @Override
    public void addTask(@NonNull Task task) {
        mTasksAdapter.addTask(task);
    }

    @Override
    public void editTask(@NonNull Task task) {
        mTasksAdapter.editTask(task);
    }
}
