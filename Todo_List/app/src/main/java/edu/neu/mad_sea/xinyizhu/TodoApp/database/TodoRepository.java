package edu.neu.mad_sea.xinyizhu.TodoApp.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import edu.neu.mad_sea.xinyizhu.TodoApp.model.ToDoModel;

public class TodoRepository {
    private ToDoRoomDatabase mTodoDatabase;

    public TodoRepository(Context context) {
        this.mTodoDatabase = ToDoRoomDatabase.getInstance(context);
    }

    public void updateTodo(ToDoModel toDoModel) {
    }

    public void insertTodo(ToDoModel toDoModel) {
    }

    public LiveData<List<ToDoModel>> retrieveTodoItem() {
        return null;
    }

    public void deleteTodo(ToDoModel toDoModel) {

    }
}
