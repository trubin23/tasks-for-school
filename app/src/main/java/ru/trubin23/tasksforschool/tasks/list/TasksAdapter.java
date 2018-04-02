package ru.trubin23.tasksforschool.tasks.list;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import ru.trubin23.tasksforschool.R;
import ru.trubin23.tasksforschool.data.Task;

/**
 * Created by Andrey on 02.04.2018.
 */

class TasksAdapter extends BaseAdapter {

    private List<Task> mTasks;

    public TasksAdapter(@NonNull List<Task> tasks) {
        mTasks = tasks;
    }

    @Override
    public int getCount() {
        return mTasks.size();
    }

    @Override
    public Object getItem(int position) {
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



        return rowView;
    }
}
