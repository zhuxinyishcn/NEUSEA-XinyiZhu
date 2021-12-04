package edu.neu.mad_sea.xinyizhu.TodoApp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import edu.neu.mad_sea.xinyizhu.TodoApp.database.TodoRepository;
import edu.neu.mad_sea.xinyizhu.TodoApp.model.ToDoModel;
import edu.neu.mad_sea.xinyizhu.TodoApp.utils.Utility;

public class CreateTask extends AppCompatActivity {

  private EditText mEditText;
  private TextView mTextView;
  private TextView mDeadline;
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
    mDeadline = findViewById(R.id.Deadline);
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
    if (getIntent().hasExtra("deadline")) {
      Log.d("TAG", "getTodoItem: " + getIntent().getParcelableExtra("deadline"));
//      this.mDeadline = getIntent().getParcelableExtra("deadline");
    }
    this.newTodoFlag = true;
    return true;
  }

  private void initTodoProperties() {
    mEditText.setText("Wirte your title!");
    mTextView.setText("Write Detail info!");
    mDeadline.setText(Utility.futureTime());
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

  public void shareText(View view) {

    StringBuilder sb = new StringBuilder();
    sb.append("Hey, your friend have a todo task to need COMPLETE!!!\n");
    sb.append(String.format("Title: %s\n", this.mToDoModel.getTitle()));
    sb.append(String.format("Detail: %s\n", this.mToDoModel.getDetail()));
    sb.append("Please remind him by this information!!! :)");
    String mimeType = "text/plain";
    ShareCompat.IntentBuilder
        .from(this)
        .setType(mimeType)
        .setChooserTitle(R.string.share_text_with)
        .setText(sb.toString())
        .startChooser();
  }
}