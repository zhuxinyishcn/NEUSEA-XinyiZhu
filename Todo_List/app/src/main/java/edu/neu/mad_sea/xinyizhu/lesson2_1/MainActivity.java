package edu.neu.mad_sea.xinyizhu.lesson2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int TEXT_REQUEST = 1;
    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE =
            "com.example.android.twoactivities.extra.MESSAGE";

    TaskDataResource taskDataResource = TaskDataResource.getInstance();
    ArrayList<String> items = new ArrayList<>();
    ArrayAdapter<String> itemsAdapter = taskDataResource.itemsAdapter;
    ListView lvItems = taskDataResource.lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = findViewById(R.id.lvItems);
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        items.add("First Item");
        items.add("Second Item");
        items.add("Third Item");
        items.add("Forth Item");
        items.add("Fifth Item");
        items.add("Sixth Item");
        setupListViewListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemsAdapter.notifyDataSetChanged();
    }

    // Attaches a long click listener to the listview
    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        // Remove the item within array at position
                        items.remove(pos);
                        // Refresh the adapter
                        itemsAdapter.notifyDataSetChanged();
                        // Return true consumes the long click event (marks it handled)
                        return true;
                    }
                });
    }

    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(EXTRA_MESSAGE, EXTRA_MESSAGE);
        startActivityForResult(intent, TEXT_REQUEST);
    }


}


