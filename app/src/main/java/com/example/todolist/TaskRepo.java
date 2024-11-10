package com.example.todolist;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepo {
    private TaskDao taskDao;
    private LiveData<List<Task>> tasklist;

    public TaskRepo(Application application) {
       TaskDatabase taskDatabase =  TaskDatabase.getInstance(application);
       taskDao = taskDatabase.taskDao();
       tasklist = taskDao.getAllTasks();
    }

    public void insertData(Task task){new InsertTask(taskDao).execute(task);}
    public void deleteData(Task task){ new DeleteTask(taskDao).execute(task);}
    public LiveData<List<Task>> getAllTasks(){
        return tasklist;
    }

    private  static class InsertTask extends AsyncTask<Task,Void,Void>{
        private TaskDao taskDao;

        public InsertTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks){
            taskDao.insert(tasks[0]);
            return null;
        }
    }

    private  static class DeleteTask extends AsyncTask<Task,Void,Void>{
        private TaskDao taskDao;

        public DeleteTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks){
            taskDao.delete(tasks[0]);
            return null;
        }
    }


}
