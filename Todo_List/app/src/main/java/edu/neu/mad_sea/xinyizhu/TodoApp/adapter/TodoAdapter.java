package edu.neu.mad_sea.xinyizhu.TodoApp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.neu.mad_sea.xinyizhu.TodoApp.MainActivity;
import edu.neu.mad_sea.xinyizhu.TodoApp.R;
import edu.neu.mad_sea.xinyizhu.TodoApp.model.ToDoModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> implements
    Filterable {

  private final MainActivity activity;
  private List<ToDoModel> toDoModelList = new ArrayList<>();
  private OnTodoListener mOnTodoListener;
  private List<ToDoModel> copy2DoList;
  private Filter todoFilter = new Filter() {
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
      List<ToDoModel> filteredList = new ArrayList<>();
      if (constraint == null || constraint.length() == 0) {
        filteredList.addAll(copy2DoList);
      } else {
        String pattern = constraint.toString().toLowerCase(Locale.ROOT).trim();
        filteredList.addAll(
            toDoModelList.stream().filter(toDoModel -> toDoModel.getTitle().trim().toLowerCase(
                Locale.ROOT).contains(pattern) || toDoModel.getDetail().trim().toLowerCase(
                Locale.ROOT).contains(pattern)).collect(Collectors.toList()));
      }
      FilterResults results = new FilterResults();
      results.values = filteredList;
      return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
      toDoModelList.clear();
      toDoModelList.addAll((List) results.values);
      notifyDataSetChanged();
    }
  };

  public TodoAdapter(MainActivity activity, OnTodoListener onTodoListener) {
    this.activity = activity;
    this.mOnTodoListener = onTodoListener;
  }

  public void setToDoModelList(List<ToDoModel> toDoModelList) {
    this.toDoModelList = toDoModelList;
    this.copy2DoList = new ArrayList<>(toDoModelList);
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
    holder.description.setText("Description:" + item.getDetail().concat("..."));
  }

  @Override
  public int getItemCount() {
    return toDoModelList.size();
  }

  @Override
  public Filter getFilter() {
    return todoFilter;
  }

  public interface OnTodoListener {

    void onTodoClick(int position);
  }

  public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    CheckBox task;
    TextView due;
    TextView description;
    OnTodoListener onTodoListener;

    ViewHolder(View view, OnTodoListener onTodoListener) {
      super(view);
      task = view.findViewById(R.id.todoCheckBox);
      due = view.findViewById(R.id.dueTime);
      description = view.findViewById(R.id.detail);
      this.onTodoListener = onTodoListener;
      view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      onTodoListener.onTodoClick(getAbsoluteAdapterPosition());
    }
  }
}
