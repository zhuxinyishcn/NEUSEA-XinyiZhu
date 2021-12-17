package edu.neu.mad_sea.xinyizhu.TodoApp;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.DialogFragment;
import edu.neu.mad_sea.xinyizhu.TodoApp.database.TodoRepository;
import edu.neu.mad_sea.xinyizhu.TodoApp.model.ToDoModel;
import edu.neu.mad_sea.xinyizhu.TodoApp.utils.TimePickerFragment;
import edu.neu.mad_sea.xinyizhu.TodoApp.utils.Utility;

public class CreateTask extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

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
    mToDoModel.setDetail(mEditText.getText().toString());
    mToDoModel.setTitle(mTextView.getText().toString());
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
    mTextView.setText(mToDoModel.getTitle());
    mEditText.setText(mToDoModel.getDetail());
    mDeadline.setText(Utility.futureTime());
  }


  public void cancelTodo(View v) {
    finish();
  }


  public void saveTodo(View view) {

    if (newTodoFlag) {
      mToDoModel = new ToDoModel(0, mTextView.getText().toString(), mEditText.getText().toString(),
          mDeadline.getText().toString());
      saveTodo2Repository();
      finish();
    } else {
      mToDoModel.setDueTime(mDeadline.getText().toString());
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

  @Override
  public void onTimeSet(TimePicker timePicker, int i, int i1) {
    mDeadline.setText(Utility.getCurrentTimestamp().substring(0, 11) + i + ":" + i1);
  }

  public void pickTime(View view) {
    DialogFragment timePicker = new TimePickerFragment();
    timePicker.show(getSupportFragmentManager(), "time picker");
  }
}
