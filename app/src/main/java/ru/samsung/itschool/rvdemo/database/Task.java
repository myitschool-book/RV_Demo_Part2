package ru.samsung.itschool.rvdemo.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class Task {

    public static final int TASK_STATUS_NOT_FINISHED = 0;
    public static final int TASK_STATUS_FINISHED = 1;
    public static final int TASK_IMPORTANCE_LOW = 0;
    public static final int TASK_IMPORTANCE_NORMAL = 1;
    public static final int TASK_IMPORTANCE_HIGH = 2;

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;

    @NonNull
    private String taskName;

    private String taskDiscription;

    private int taskStatus;

    private int taskImportance;

    public Task(@NonNull String taskName, String taskDiscription, int taskStatus, int taskImportance) {
        this.taskName = taskName;
        setTaskDiscription(taskDiscription);
        setTaskStatus(taskStatus);
        setTaskImportance(taskImportance);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(@NonNull String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDiscription() {
        return taskDiscription;
    }

    public void setTaskDiscription(String taskDiscription) {
        if (taskDiscription == null) { taskDiscription = ""; }
        this.taskDiscription = taskDiscription;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        if (taskStatus != TASK_STATUS_NOT_FINISHED || taskStatus != TASK_STATUS_FINISHED) { taskStatus = TASK_STATUS_NOT_FINISHED; }
        this.taskStatus = taskStatus;
    }

    public int getTaskImportance() {
        return taskImportance;
    }

    public void setTaskImportance(int taskImportance) {
        if (taskImportance < TASK_IMPORTANCE_LOW || taskImportance > TASK_IMPORTANCE_HIGH) { taskImportance = TASK_IMPORTANCE_NORMAL; }
        this.taskImportance = taskImportance;
    }

}
