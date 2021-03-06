package edu.neu.mad_sea.xinyizhu.TodoApp.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import edu.neu.mad_sea.xinyizhu.TodoApp.model.ToDoModel;

/**
 * The type To do room database.
 */
@Database(entities = {ToDoModel.class}, exportSchema = false, version = 3)
public abstract class ToDoRoomDatabase extends RoomDatabase {

  /**
   * The constant DATABASE_NAME.
   */
  public static final String DATABASE_NAME = "todo_db";
  private static ToDoRoomDatabase instance;

  /**
   * Gets instance.
   *
   * @param context the context
   * @return the instance
   */
  static ToDoRoomDatabase getInstance(final Context context) {
    if (instance == null) {
      instance = Room
          .databaseBuilder(context.getApplicationContext(), ToDoRoomDatabase.class, DATABASE_NAME)
          .build();
    }
    return instance;
  }

  /**
   * Gets todo dao.
   *
   * @return the todo dao
   */
  public abstract TodoItemDao getTodoDao();

}