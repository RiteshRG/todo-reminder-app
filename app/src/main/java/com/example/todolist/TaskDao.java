package com.example.todolist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao{
    @Insert
    public void insert(Task task);

    @Delete
    public void delete(Task task);

    @Query("SELECT * FROM tasks")
    public LiveData<List<Task>> getAllTasks();
}
