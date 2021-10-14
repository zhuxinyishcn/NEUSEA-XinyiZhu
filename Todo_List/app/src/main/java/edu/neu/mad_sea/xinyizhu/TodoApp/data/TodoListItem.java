package edu.neu.mad_sea.xinyizhu.TodoApp.data;

import java.util.HashSet;

public class TodoListItem {
    private String taskTitle;
    private String detail;
    private HashSet<String> tags;
    private boolean rememberFlag;
    private String notificationTime;

    public TodoListItem(String taskTitle, String detail) {
        this.taskTitle = taskTitle;
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "TodoListItem{" +
                "taskTitle='" + taskTitle + '\'' +
                ", detail='" + detail + '\'' +
                ", tags=" + tags +
                ", rememberFlag=" + rememberFlag +
                ", notificationTime='" + notificationTime + '\'' +
                '}';
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public HashSet<String> getTags() {
        return tags;
    }

    public void setTags(HashSet<String> tags) {
        this.tags = tags;
    }

    public boolean isRememberFlag() {
        return rememberFlag;
    }

    public void setRememberFlag(boolean rememberFlag) {
        this.rememberFlag = rememberFlag;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }
}
