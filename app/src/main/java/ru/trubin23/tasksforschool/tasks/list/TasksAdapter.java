package ru.trubin23.tasksforschool.tasks.list;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ru.trubin23.tasksforschool.R;
import ru.trubin23.tasksforschool.data.Task;

/**
 * Created by Andrey on 02.04.2018.
 */

class TasksAdapter extends BaseAdapter {

    private List<Task> mTasks;
    private TaskItemListener mTaskItemListener;

    public TasksAdapter(@NonNull List<Task> tasks, @NonNull TaskItemListener taskItemListener) {
        mTasks = tasks;
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

        TextView dateTV = rowView.findViewById(R.id.item_date);
        titleTV.setText(task.getDateOfCreation());

        mTaskItemListener.onTaskClick(task);

        return rowView;
    }
}
