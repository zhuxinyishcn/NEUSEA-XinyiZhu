package edu.neu.mad_sea.xinyizhu.TodoApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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
    setSpinner();
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

  private void setSpinner() {
    Spinner spinner = findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
    spinner.setAdapter(adapter);
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
      mToDoModel.setDueTime(Utility.futureTime());
      updateNote();
    }
  }
}