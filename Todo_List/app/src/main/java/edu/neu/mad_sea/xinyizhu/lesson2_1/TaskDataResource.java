package edu.neu.mad_sea.xinyizhu.lesson2_1;

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

    public void addItem(String item) {
        itemsAdapter.add(item);
        itemsAdapter.notifyDataSetChanged();
    }

}
