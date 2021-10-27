package edu.neu.mad_sea.xinyizhu.TodoApp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import edu.neu.mad_sea.xinyizhu.TodoApp.database.TodoRepository;
import edu.neu.mad_sea.xinyizhu.TodoApp.model.ToDoModel;

public class CreateTask extends AppCompatActivity implements TextWatcher {

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
    mTextView.addTextChangedListener(this);
    if (getTodoItem()) {
      // This is a create a todo item
      initTodoProperties();
    } else {
      setTodoProperties();
    }
  }

  private void updateNote() {
//    mTodoRepository.updateTodo();
  }

  private void saveTodo() {
    if (newTodoFlag) {
      saveTodo2Repository();
    } else {
      updateNote();
    }
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

  @Override
  public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

  }

  @Override
  public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//    mTextView.setText(charSequence.toString());
  }

  @Override
  public void afterTextChanged(Editable editable) {

  }
}