package edu.neu.mad_sea.xinyizhu.TodoApp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class CreateTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_task_layout);
        final Intent i = new Intent(CreateTask.this, MainActivity.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(i);
                finish();
            }
        }, 1000);
    }

//    public void onAddItem(View v) {
//        EditText etNewItem = (EditText) findViewById(R.id.Task);
//        String itemText = etNewItem.getText().toString();
//        TaskDataResource.items.add(itemText);
//        Log.d("list", TaskDataResource.items.toString());
//        finish();
//    }

}