package ru.trubin23.tasksforschool.tasks.list;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.trubin23.tasksforschool.R;
import ru.trubin23.tasksforschool.data.Task;
import ru.trubin23.tasksforschool.util.ColorUtils;

/**
 * Created by Andrey on 02.04.2018.
 */

public class TasksAdapter extends BaseAdapter {

    private List<Task> mTasks;
    private TaskItemListener mTaskItemListener;

    public TasksAdapter(@Nullable List<Task> tasks, @NonNull TaskItemListener taskItemListener) {
        if (tasks != null) {
            mTasks = tasks;
        } else {
            mTasks = new ArrayList<>();
        }
        mTaskItemListener = taskItemListener;
    }

    @Override
    public int getCount() {
        return mTasks.size();
    }

    @Override
    public Task getItem(int position) {
        return mTasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View rowView = view;
        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            rowView = inflater.inflate(R.layout.task_item, viewGroup, false);
        }

        Task task = getItem(position);

        TextView titleTV = rowView.findViewById(R.id.item_title);
        titleTV.setText(task.getTitle());
        titleTV.setTextColor(ColorUtils.colorText(task.getColor()));

        TextView dateTV = rowView.findViewById(R.id.item_date);
        dateTV.setText(task.getDateOfCreation());
        dateTV.setTextColor(ColorUtils.colorText(task.getColor()));

        rowView.setBackgroundColor(task.getColor());

        rowView.setOnClickListener(clickView -> mTaskItemListener.onTaskClick(task));

        return rowView;
    }

    @NonNull
    public List<Task> getTasks() {
        return mTasks;
    }

    public void addTask(@NonNull Task task) {
        if (getPositionByTaskId(task.getId()) < 0) {
            mTasks.add(task);
            notifyDataSetChanged();
        }
    }

    public void editTask(@NonNull Task task) {
        int position = getPositionByTaskId(task.getId());
        if (position >= 0) {
            mTasks.set(position, task);
            notifyDataSetChanged();
        }
    }

    private int getPositionByTaskId(int taskId) {
        for (int i=0; i<mTasks.size();i++) {
            if (getItem(i).getId() == taskId) {
                return i;
            }
        }

        return -1;
    }
}
