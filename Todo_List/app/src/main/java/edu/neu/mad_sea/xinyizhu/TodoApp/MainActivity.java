package edu.neu.mad_sea.xinyizhu.TodoApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.neu.mad_sea.xinyizhu.TodoApp.adapter.TodoAdapter;
import edu.neu.mad_sea.xinyizhu.TodoApp.database.TodoRepository;
import edu.neu.mad_sea.xinyizhu.TodoApp.model.ToDoModel;
import edu.neu.mad_sea.xinyizhu.TodoApp.utils.Utility;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TodoAdapter.OnTodoListener {

  private List<ToDoModel> taskList = new ArrayList<>();
  private RecyclerView taskRecyclerView;
  private TodoAdapter todoAdapter;
  private TodoRepository mTodoRepository;
  private ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,
      ItemTouchHelper.RIGHT) {
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
        @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
      return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
      deleteTodo(taskList.get(viewHolder.getAbsoluteAdapterPosition()));
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    taskRecyclerView = findViewById(R.id.taskRecycleView);
    this.mTodoRepository = new TodoRepository(this);
    initRecycleView();
    retrieveNotes();
  }

  private void initRecycleView() {
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    taskRecyclerView.setLayoutManager(linearLayoutManager);
    new ItemTouchHelper(simpleCallback).attachToRecyclerView(taskRecyclerView);
    todoAdapter = new TodoAdapter(this, this);
    taskRecyclerView.setAdapter(todoAdapter);

    mTodoRepository
        .insertTodo(new ToDoModel(0, "test", "some detail info", Utility.getCurrentTimestamp()));
  }

  private void retrieveNotes() {
    mTodoRepository.retrieveTodoItem().observe(this, new Observer<List<ToDoModel>>() {
      @Override
      public void onChanged(List<ToDoModel> toDoModels) {
        if (taskList.size() > 0) {
          taskList.clear();
        }
        if (toDoModels != null) {
          taskList.addAll(toDoModels);
          todoAdapter.setToDoModelList(taskList);
        }
        todoAdapter.notifyDataSetChanged();
      }
    });
  }

  @Override
  public void onTodoClick(int position) {
    Intent intent = new Intent(this, CreateTask.class);
    intent.putExtra("todo_item", taskList.get(position));
    startActivity(intent);
  }

  public void createTodo(View view) {
    Intent intent = new Intent(this, CreateTask.class);
    startActivity(intent);
  }

  private void deleteTodo(ToDoModel toDoModel) {
    taskList.remove(toDoModel);
    Toast toast = Toast.makeText(this, toDoModel.getTitle() + "Delete!:)", Toast.LENGTH_LONG);
    toast.show();
    mTodoRepository.deleteTodo(toDoModel);
    todoAdapter.notifyDataSetChanged();
  }


}


