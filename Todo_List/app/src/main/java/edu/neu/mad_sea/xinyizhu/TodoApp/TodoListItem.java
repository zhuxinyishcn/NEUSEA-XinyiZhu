package edu.neu.mad_sea.xinyizhu.TodoApp;

import java.util.HashSet;

public class TodoListItem {
    private String taskTitle;
    private String detail;
    private HashSet<String> tags;
    private boolean rememberFlag;
    private String notificationTime;

    public TodoListItem(String taskTitle, String detail, HashSet<String> tags, boolean rememberFlag, String notificationTime) {
        this.taskTitle = taskTitle;
        this.detail = detail;
        this.tags = tags;
        this.rememberFlag = rememberFlag;
        this.notificationTime = notificationTime;
    }

}
