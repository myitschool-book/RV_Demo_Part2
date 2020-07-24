package ru.samsung.itschool.rvdemo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import ru.samsung.itschool.rvdemo.database.Task;
import ru.samsung.itschool.rvdemo.database.TaskDao;
import ru.samsung.itschool.rvdemo.database.TaskRoomDatabase;

public class TaskViewModel extends AndroidViewModel {

    private TaskRoomDatabase taskRoomDatabase;
    private TaskDao taskDao;

    public LiveData<Task> task;

    private MutableLiveData<Boolean> _eventTaskAdd = new MutableLiveData<>();
    public LiveData<Boolean> getEventTaskAdd() { return _eventTaskAdd; }

    private MutableLiveData<Boolean> _eventTaskUpdate = new MutableLiveData<>();

    public LiveData<Boolean> getEventTaskUpdate() { return _eventTaskUpdate; }

    public void eventTaskAddFinished() {
        this._eventTaskAdd.setValue(false);
    }

    public void eventTaskUpdateFinished() {
        this._eventTaskUpdate.setValue(false);
    }

    public TaskViewModel(@NonNull Application application) {
        super(application);

        taskRoomDatabase = TaskRoomDatabase.getDatabase(application);
        taskDao = taskRoomDatabase.taskDao();

        //task = taskDao.getTask(5);

        _eventTaskAdd.setValue(false);

    }

    public void insertTask(Task task) {
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> taskDao.insert(task));
        _eventTaskAdd.setValue(true);
    }

    public void getTask(long id) {
        task = taskDao.getTask(id);
    }

    public void updateTask() {
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> taskDao.updateTask(task.getValue()));
        _eventTaskUpdate.setValue(true);
    }
}
