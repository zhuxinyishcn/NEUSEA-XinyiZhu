package edu.neu.mad_sea.xinyizhu.TodoApp;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class TaskDataResource {
    private static TaskDataResource instance;
    static ArrayAdapter<String> itemsAdapter;
    static ListView lvItems;
    static ArrayList<String> items = new ArrayList<>();

    private TaskDataResource() {
    }


    public static TaskDataResource getInstance() {
        if (instance == null) {
            instance = new TaskDataResource();
        }
        return instance;
    }

    public static void addItem(String item) {
        MainActivity.itemsAdapter.add(item);
        itemsAdapter.notifyDataSetChanged();
    }

}
