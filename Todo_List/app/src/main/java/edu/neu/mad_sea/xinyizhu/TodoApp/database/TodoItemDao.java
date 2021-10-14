package edu.neu.mad_sea.xinyizhu.TodoApp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import edu.neu.mad_sea.xinyizhu.TodoApp.model.ToDoModel;

@Dao
public interface TodoItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ToDoModel todo);

    @Query("DELETE FROM todo")
    void deleteAll();

    @Query("SELECT * FROM todo")
    LiveData<List<ToDoModel>> getTodos();

    // Selects n elements from the table;
    // Does not specify order (e.g. most recent n items).
    @Query("SELECT * FROM todo LIMIT :n")
    LiveData<List<ToDoModel>> getNTodos(int n);
}
