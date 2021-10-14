package edu.neu.mad_sea.xinyizhu.TodoApp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.neu.mad_sea.xinyizhu.TodoApp.MainActivity;
import edu.neu.mad_sea.xinyizhu.TodoApp.model.ToDoModel;
import edu.neu.mad_sea.xinyizhu.TodoApp.R;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
    private final MainActivity activity;
    private List<ToDoModel> toDoModelList;

    public TodoAdapter(MainActivity activity) {
        this.activity = activity;
    }

    public void setToDoModelList(List<ToDoModel> toDoModelList) {
        this.toDoModelList = toDoModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TodoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_items, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.ViewHolder holder, int position) {
        ToDoModel item = toDoModelList.get(position);
        holder.task.setText(item.getTitle());
        // check if complete
        holder.task.setChecked(item.getStatus() != 0);
    }

    @Override
    public int getItemCount() {
        return toDoModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox task;

        ViewHolder(View view) {
            super(view);
            task = view.findViewById(R.id.todoCheckBox);
        }
    }
}
