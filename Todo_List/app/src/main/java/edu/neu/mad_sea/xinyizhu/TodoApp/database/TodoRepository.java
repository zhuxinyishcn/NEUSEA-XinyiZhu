package edu.neu.mad_sea.xinyizhu.TodoApp.database;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.neu.mad_sea.xinyizhu.TodoApp.async.InsertAsyncTask;
import edu.neu.mad_sea.xinyizhu.TodoApp.model.ToDoModel;
import java.util.List;

public class TodoRepository {

  private ToDoRoomDatabase mTodoDatabase;

  public TodoRepository(Context context) {
    this.mTodoDatabase = ToDoRoomDatabase.getInstance(context);
  }

  public void updateTodo(ToDoModel toDoModel) {
  }

  public void insertTodo(ToDoModel toDoModel) {
    new InsertAsyncTask(mTodoDatabase.getTodoDao()).execute(toDoModel);
  }

  public LiveData<List<ToDoModel>> retrieveTodoItem() {
    return mTodoDatabase.getTodoDao().getTodos();
  }

  public void deleteTodo(ToDoModel toDoModel) {

  }
}
