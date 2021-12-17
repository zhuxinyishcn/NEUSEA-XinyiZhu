package edu.neu.mad_sea.xinyizhu.TodoApp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import edu.neu.mad_sea.xinyizhu.TodoApp.adapter.TodoAdapter;
import edu.neu.mad_sea.xinyizhu.TodoApp.database.TodoRepository;
import edu.neu.mad_sea.xinyizhu.TodoApp.model.ToDoModel;
import edu.neu.mad_sea.xinyizhu.TodoApp.notification.AlertReceiver;
import edu.neu.mad_sea.xinyizhu.TodoApp.utils.Utility;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity implements TodoAdapter.OnTodoListener {

  private List<ToDoModel> taskList = new ArrayList<>();
  private RecyclerView taskRecyclerView;
  private TodoAdapter todoAdapter;
  private TodoRepository mTodoRepository;
  private final ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(
      ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.START
          | ItemTouchHelper.END,
      ItemTouchHelper.RIGHT) {
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
        @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
      int fromPosition = viewHolder.getLayoutPosition();
      int toPosition = target.getLayoutPosition();
      Collections.swap(taskList, fromPosition, toPosition);
      Objects.requireNonNull(recyclerView.getAdapter()).notifyItemMoved(fromPosition, toPosition);
      return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
      deleteTodo(taskList.get(viewHolder.getAbsoluteAdapterPosition()));
    }
  };
  private GoogleSignInClient mGoogleSignInClient;
  private int RC_SIGN_IN = 0;
  private View checkBox;

  /**
   * On create.
   *
   * @param savedInstanceState the saved instance state
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    taskRecyclerView = findViewById(R.id.taskRecycleView);
    this.mTodoRepository = new TodoRepository(this);
    initRecycleView();
    retrieveNotes();
    // Configure sign-in to request the user's ID, email address, and basic
    // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build();
    mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//    SignInButton signInButton = findViewById(R.id.sign_in_button);
//    signInButton.setOnClickListener(view -> signIn());
  }


  private void initRecycleView() {
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    taskRecyclerView.setLayoutManager(linearLayoutManager);
    new ItemTouchHelper(simpleCallback).attachToRecyclerView(taskRecyclerView);
    todoAdapter = new TodoAdapter(this, this);
    taskRecyclerView.setAdapter(todoAdapter);
  }

  private void retrieveNotes() {
    mTodoRepository.retrieveTodoItem().observe(this, toDoModels -> {
      if (taskList.size() > 0) {
        taskList.clear();
      }
      if (toDoModels != null) {
        taskList.addAll(toDoModels);
        todoAdapter.setToDoModelList(taskList);
        // todo: test to remind people
        setReminder4TodoItem();
      }
      todoAdapter.notifyDataSetChanged();
    });

  }

  /**
   * On todo click.
   *
   * @param position the position
   */
  @Override
  public void onTodoClick(int position) {
    Intent intent = new Intent(this, CreateTask.class);
    intent.putExtra("todo_item", taskList.get(position));
    startActivity(intent);
  }

  /**
   * Create todo.
   *
   * @param view the view
   */
  public void createTodo(View view) {
    Intent intent = new Intent(this, CreateTask.class);
    startActivity(intent);
  }

  private void deleteTodo(ToDoModel toDoModel) {
    taskList.remove(toDoModel);
    Toast toast = Toast
        .makeText(this, toDoModel.getTitle() + " completely done!:)", Toast.LENGTH_LONG);
    toast.show();
    mTodoRepository.deleteTodo(toDoModel);
    todoAdapter.notifyDataSetChanged();
  }

  /**
   * Send channel.
   *
   * @param time the time
   */
  public void sendChannel(String time) {
    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent(this, AlertReceiver.class);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
    Calendar cal = Utility.date2Cal(time);
    alarmManager.setExact(AlarmManager.RTC, cal.getTimeInMillis(), pendingIntent);
  }

  private void setReminder4TodoItem() {
    taskList.forEach(toDoModel -> sendChannel(toDoModel.getDueTime()));
  }

  /**
   * On destroy.
   */
  @Override
  protected void onDestroy() {
    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent(this, AlertReceiver.class);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
    alarmManager.cancel(pendingIntent);
    super.onDestroy();
  }

  private void signIn() {
    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
    startActivityForResult(signInIntent, RC_SIGN_IN);
  }

  /**
   * On activity result.
   *
   * @param requestCode the request code
   * @param resultCode  the result code
   * @param data        the data
   */
  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
    if (requestCode == RC_SIGN_IN) {
      // The Task returned from this call is always completed, no need to attach
      // a listener.
      Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
      handleSignInResult(task);
    }
  }

  private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
    try {
      GoogleSignInAccount account = completedTask.getResult(ApiException.class);
      // Signed in successfully, show authenticated UI.
//      Intent i = new Intent(MainActivity.this, pitcu.class);
//      startActivity(i);
    } catch (ApiException e) {
      // The ApiException status code indicates the detailed failure reason.
      // Please refer to the GoogleSignInStatusCodes class reference for more information.
      Log.w("Login:",
          "signInResult:failed code=" + e.getStatusCode() + " messaga" + e.getMessage());
    }
  }

  /**
   * On create options menu boolean.
   *
   * @param menu the menu
   * @return the boolean
   */
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.todo_menu, menu);
    MenuItem targetItem = menu.findItem(R.id.action_search);
    SearchView searchView = (SearchView) targetItem.getActionView();
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        todoAdapter.getFilter().filter(newText);
        return false;
      }
    });
    return true;
  }
}


