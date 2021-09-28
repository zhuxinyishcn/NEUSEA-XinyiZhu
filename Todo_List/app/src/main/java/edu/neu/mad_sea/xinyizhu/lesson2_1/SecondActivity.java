package edu.neu.mad_sea.xinyizhu.lesson2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TaskDataResource taskDataResource = TaskDataResource.getInstance();
    private ArrayAdapter<String> itemsAdapter = taskDataResource.itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        Intent intent = getIntent();
//        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
//        TextView textView = findViewById(R.id.text_message);
//        textView.setText(message);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.Task);
        String itemText = etNewItem.getText().toString();
        Log.d(MainActivity.class.getSimpleName(), itemText);
//        MainActivity.itemsAdapter.add(itemText);
        taskDataResource.addItem(itemText);
    }

}