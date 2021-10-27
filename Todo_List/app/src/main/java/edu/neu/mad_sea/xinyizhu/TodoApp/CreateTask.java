package edu.neu.mad_sea.xinyizhu.TodoApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import edu.neu.mad_sea.xinyizhu.TodoApp.database.TodoRepository;
import edu.neu.mad_sea.xinyizhu.TodoApp.model.ToDoModel;
import edu.neu.mad_sea.xinyizhu.TodoApp.utils.Utility;

public class CreateTask extends AppCompatActivity {

  private EditText mEditText;
  private TextView mTextView;

  private ToDoModel mToDoModel;
  private boolean newTodoFlag = false;
  private TodoRepository mTodoRepository;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.create_task_layout);
    final Intent i = new Intent(CreateTask.this, MainActivity.class);
    mEditText = findViewById(R.id.TaskText);
    mTextView = findViewById(R.id.TaskDetail);
    mTodoRepository = new TodoRepository(this);
    if (getTodoItem()) {
      // This is a create a todo item
      initTodoProperties();
    } else {
      setTodoProperties();
    }
  }

  private void updateNote() {
    mToDoModel.setTitle(mTextView.getText().toString());
    mToDoModel.setDetail(mEditText.getText().toString());

    mTodoRepository.updateTodo(mToDoModel);
  }


  private void saveTodo2Repository() {
    mTodoRepository.insertTodo(mToDoModel);
  }

  private boolean getTodoItem() {
    if (getIntent().hasExtra("todo_item")) {
      this.mToDoModel = getIntent().getParcelableExtra("todo_item");
      return newTodoFlag;
    }
    this.newTodoFlag = true;
    return true;
  }

  private void initTodoProperties() {
    mEditText.setText("Wirte your title!");
    mTextView.setText("Write Detail info!");
  }

  private void setTodoProperties() {
    mEditText.setText(mToDoModel.getTitle());
    mTextView.setText(mToDoModel.getDetail());
  }


  public void cancelTodo(View v) {
    finish();
  }


  public void saveTodo(View view) {
    if (newTodoFlag) {
      mToDoModel = new ToDoModel(1, mTextView.getText().toString(), mEditText.getText().toString(),
          Utility.getCurrentTimestamp());
      saveTodo2Repository();
      finish();
    } else {
      updateNote();
    }
  }
}