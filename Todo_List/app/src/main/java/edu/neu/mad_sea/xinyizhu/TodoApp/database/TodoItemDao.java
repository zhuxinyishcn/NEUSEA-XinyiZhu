package edu.neu.mad_sea.xinyizhu.TodoApp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import edu.neu.mad_sea.xinyizhu.TodoApp.model.ToDoModel;
import java.util.List;

/**
 * The interface Todo item dao.
 */
@Dao
public interface TodoItemDao {

  /**
   * Insert long [ ].
   *
   * @param todo the todo
   * @return the long [ ]
   */
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  long[] insert(ToDoModel... todo);

  /**
   * Delete int.
   *
   * @param todo the todo
   * @return the int
   */
  @Delete
  int delete(ToDoModel... todo);

  /**
   * Gets todos.
   *
   * @return the todos
   */
  @Query("SELECT * FROM todo")
  LiveData<List<ToDoModel>> getTodos();

  /**
   * Gets n todos.
   *
   * @param n the n
   * @return the n todos
   */
// Selects n elements from the table Does not specify order (e.g. most recent n items)
  @Query("SELECT * FROM todo LIMIT :n")
  LiveData<List<ToDoModel>> getNTodos(int n);


  /**
   * Update int.
   *
   * @param todo the todo
   * @return the int
   */
  @Update
  int update(ToDoModel... todo);
}
