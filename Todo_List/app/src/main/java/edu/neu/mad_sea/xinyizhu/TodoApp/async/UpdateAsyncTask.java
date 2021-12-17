package edu.neu.mad_sea.xinyizhu.TodoApp.async;

import android.os.AsyncTask;
import edu.neu.mad_sea.xinyizhu.TodoApp.database.TodoItemDao;
import edu.neu.mad_sea.xinyizhu.TodoApp.model.ToDoModel;

/**
 * The type Update async task.
 */
public class UpdateAsyncTask extends AsyncTask<ToDoModel, Void, Void> {

  private TodoItemDao mtTdoItemDao;

  /**
   * Instantiates a new Update async task.
   *
   * @param dao the dao
   */
  public UpdateAsyncTask(TodoItemDao dao) {
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
    mtTdoItemDao.update(toDoModels);
    return null;
  }
}
