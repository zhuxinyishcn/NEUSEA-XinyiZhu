package edu.neu.mad_sea.xinyizhu.TodoApp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.neu.mad_sea.xinyizhu.TodoApp.MainActivity;
import edu.neu.mad_sea.xinyizhu.TodoApp.R;
import edu.neu.mad_sea.xinyizhu.TodoApp.model.ToDoModel;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

  private final MainActivity activity;
  private List<ToDoModel> toDoModelList;
  private OnTodoListener mOnTodoListener;

  public TodoAdapter(MainActivity activity, OnTodoListener onTodoListener) {
    this.activity = activity;
    this.mOnTodoListener = onTodoListener;
  }

  public void setToDoModelList(List<ToDoModel> toDoModelList) {
    this.toDoModelList = toDoModelList;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public TodoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.task_items, parent, false);
    return new ViewHolder(itemView, mOnTodoListener);

  }

  @Override
  public void onBindViewHolder(@NonNull TodoAdapter.ViewHolder holder, int position) {
    ToDoModel item = toDoModelList.get(position);
    // check if complete
    holder.task.setChecked(item.getStatus() != 0);
    holder.task.setText(item.getTitle());
    holder.due.setText(item.getDueTime());
  }

  @Override
  public int getItemCount() {

    return toDoModelList.size();
  }

  public interface OnTodoListener {

    void onTodoClick(int position);
  }

  public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    CheckBox task;
    TextView due;
    OnTodoListener onTodoListener;

    ViewHolder(View view, OnTodoListener onTodoListener) {
      super(view);
      task = view.findViewById(R.id.todoCheckBox);
      due = view.findViewById(R.id.dueTime);
      this.onTodoListener = onTodoListener;
      view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      onTodoListener.onTodoClick(getAbsoluteAdapterPosition());
    }
  }
}
