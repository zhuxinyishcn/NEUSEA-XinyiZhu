package edu.neu.mad_sea.xinyizhu.TodoApp.async;

import android.os.AsyncTask;
import edu.neu.mad_sea.xinyizhu.TodoApp.database.TodoItemDao;
import edu.neu.mad_sea.xinyizhu.TodoApp.model.ToDoModel;

public class DeleteAsyncTask extends AsyncTask<ToDoModel, Void, Void> {

  private TodoItemDao mtTdoItemDao;

  public DeleteAsyncTask(TodoItemDao dao) {
    mtTdoItemDao = dao;
  }

  @Override
  protected Void doInBackground(ToDoModel... toDoModels) {
    mtTdoItemDao.delete(toDoModels);
    return null;
  }
}
