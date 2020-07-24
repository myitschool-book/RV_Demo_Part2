package ru.samsung.itschool.rvdemo;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ru.samsung.itschool.rvdemo.database.Task;
import ru.samsung.itschool.rvdemo.database.TaskDao;
import ru.samsung.itschool.rvdemo.database.TaskRoomDatabase;

import static ru.samsung.itschool.rvdemo.database.TaskRoomDatabase.databaseWriteExecutor;

public class TasksViewModel extends AndroidViewModel {

    private TaskRoomDatabase taskRoomDatabase;
    private TaskDao taskDao;

    public LiveData<List<Task>> tasksList;

    public TasksViewModel(Application application) {
        super(application);

        taskRoomDatabase = TaskRoomDatabase.getDatabase(application);
        taskDao = taskRoomDatabase.taskDao();

        tasksList = taskDao.getAllTasks();
    }

    public void insertTask(Task task) { TaskRoomDatabase.databaseWriteExecutor.execute(() -> taskDao.insert(task)); }

    public void updateTask(Task task) { databaseWriteExecutor.execute(() -> taskDao.updateTask(task)); }

    public void deleteAllTask() { TaskRoomDatabase.databaseWriteExecutor.execute(() -> taskDao.deleteAll()); }

}
