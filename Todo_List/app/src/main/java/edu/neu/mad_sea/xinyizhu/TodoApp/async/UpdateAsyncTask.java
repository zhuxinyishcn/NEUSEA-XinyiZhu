package edu.neu.mad_sea.xinyizhu.TodoApp.async;

import android.os.AsyncTask;
import edu.neu.mad_sea.xinyizhu.TodoApp.database.TodoItemDao;
import edu.neu.mad_sea.xinyizhu.TodoApp.model.ToDoModel;

public class UpdateAsyncTask extends AsyncTask<ToDoModel, Void, Void> {

  private TodoItemDao mtTdoItemDao;

  public UpdateAsyncTask(TodoItemDao dao) {
    mtTdoItemDao = dao;
  }

  @Override
  protected Void doInBackground(ToDoModel... toDoModels) {
    mtTdoItemDao.update(toDoModels);
    return null;
  }
}
