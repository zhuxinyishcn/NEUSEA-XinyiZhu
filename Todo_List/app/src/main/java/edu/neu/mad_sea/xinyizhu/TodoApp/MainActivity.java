package edu.neu.mad_sea.xinyizhu.TodoApp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.neu.mad_sea.xinyizhu.TodoApp.adapter.TodoAdapter;
import edu.neu.mad_sea.xinyizhu.TodoApp.model.ToDoModel;

public class MainActivity extends AppCompatActivity {
    private final List<ToDoModel> taskList = new ArrayList<>();
    private RecyclerView taskRecyclerView;
    private TodoAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taskRecyclerView = findViewById(R.id.taskRecycleView);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        todoAdapter = new TodoAdapter(this);
        taskRecyclerView.setAdapter(todoAdapter);
        ToDoModel test = new ToDoModel(1,0,"test", "some detail info");
        taskList.add(test);
        taskList.add(test);
        taskList.add(test);
        taskList.add(test);
        todoAdapter.setToDoModelList(taskList);
    }


//    public void launchSecondActivity(View view) {
//        Intent intent = new Intent(this, SecondActivity.class);
//        startActivityForResult(intent, TEXT_REQUEST);
//    }


}


