package edu.neu.mad_sea.xinyizhu.TodoApp.async;

import android.os.AsyncTask;
import edu.neu.mad_sea.xinyizhu.TodoApp.database.TodoItemDao;
import edu.neu.mad_sea.xinyizhu.TodoApp.model.ToDoModel;

/**
 * The type Insert async task.
 */
public class InsertAsyncTask extends AsyncTask<ToDoModel, Void, Void> {

  private TodoItemDao mtTdoItemDao;

  /**
   * Instantiates a new Insert async task.
   *
   * @param dao the dao
   */
  public InsertAsyncTask(TodoItemDao dao) {
    mtTdoItemDao = dao;
  }

  /**
   * Do in background void.
   *
   * @param toDoModels the to do models
   * @return the void
   */
  @Override
  protected Void doInBackground(ToDoModel... toDoModels) {
    mtTdoItemDao.insert(toDoModels);
    return null;
  }
}
