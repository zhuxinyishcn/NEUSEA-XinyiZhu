package edu.neu.mad_sea.xinyizhu.TodoApp;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class TaskDataResource {
    private static TaskDataResource instance;
    static ArrayList<String> items = new ArrayList<>();

    private TaskDataResource(ArrayList<String> items) {
        TaskDataResource.items = items;
    }


    public static TaskDataResource getInstance(ArrayList<String> items) {
        if (instance == null) {
            instance = new TaskDataResource(items);
        }
        return instance;
    }


}
