package edu.neu.mad_sea.xinyizhu.TodoApp.database;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.neu.mad_sea.xinyizhu.TodoApp.async.DeleteAsyncTask;
import edu.neu.mad_sea.xinyizhu.TodoApp.async.InsertAsyncTask;
import edu.neu.mad_sea.xinyizhu.TodoApp.async.UpdateAsyncTask;
import edu.neu.mad_sea.xinyizhu.TodoApp.model.ToDoModel;
import java.util.List;

/**
 * The type Todo repository.
 */
public class TodoRepository {

  private ToDoRoomDatabase mTodoDatabase;

  /**
   * Instantiates a new Todo repository.
   *
   * @param context the context
   */
  public TodoRepository(Context context) {
    this.mTodoDatabase = ToDoRoomDatabase.getInstance(context);
  }

  /**
   * Update todo.
   *
   * @param toDoModel the to do model
   */
  public void updateTodo(ToDoModel toDoModel) {
    new UpdateAsyncTask(mTodoDatabase.getTodoDao()).execute(toDoModel);
  }

  /**
   * Insert todo.
   *
   * @param toDoModel the to do model
   */
  public void insertTodo(ToDoModel toDoModel) {
    new InsertAsyncTask(mTodoDatabase.getTodoDao()).execute(toDoModel);
  }

  /**
   * Retrieve todo item live data.
   *
   * @return the live data
   */
  public LiveData<List<ToDoModel>> retrieveTodoItem() {
    return mTodoDatabase.getTodoDao().getTodos();
  }

  /**
   * Delete todo.
   *
   * @param toDoModel the to do model
   */
  public void deleteTodo(ToDoModel toDoModel) {
    new DeleteAsyncTask(mTodoDatabase.getTodoDao()).execute(toDoModel);
  }
}
