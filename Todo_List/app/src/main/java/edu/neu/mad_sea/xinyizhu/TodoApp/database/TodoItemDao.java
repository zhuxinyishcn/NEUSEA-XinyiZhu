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

@Dao
public interface TodoItemDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  long[] insert(ToDoModel... todo);

  @Delete
  int delete(ToDoModel... todo);

  @Query("SELECT * FROM todo")
  LiveData<List<ToDoModel>> getTodos();

  // Selects n elements from the table Does not specify order (e.g. most recent n items)
  @Query("SELECT * FROM todo LIMIT :n")
  LiveData<List<ToDoModel>> getNTodos(int n);


  @Update
  int update(ToDoModel... todo);
}
