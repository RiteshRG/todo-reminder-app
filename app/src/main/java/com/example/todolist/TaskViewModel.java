package com.example.todolist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepo taskRepo;
    private LiveData<List<Task>> tasklist;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        taskRepo = new TaskRepo(application);
        tasklist = taskRepo.getAllTasks();
    }

    public void insert(Task task){
        taskRepo.insertData(task);
    }

    public void delete(Task task){
        taskRepo.deleteData(task);
    }

    public LiveData<List<Task>> getAllData() {
        return taskRepo.getAllTasks();
    }
}
