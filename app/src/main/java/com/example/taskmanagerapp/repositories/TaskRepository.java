package com.example.taskmanagerapp.repositories;

import android.content.Context;

import com.example.taskmanagerapp.dao.TaskDao;
import com.example.taskmanagerapp.database.TaskDatabase;
import com.example.taskmanagerapp.entities.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;

public class TaskRepository {
    private TaskDao taskDao;
    private LiveData<List<Task>> allTasks;
    private ExecutorService executorService;

    // Constructor to initialize the repository
    public TaskRepository(Context context) {
        TaskDatabase database = TaskDatabase.getInstance(context);
        taskDao = database.taskDao();
        allTasks = taskDao.getAllTasks();
        executorService = Executors.newFixedThreadPool(2);
    }

    // Method to get all tasks
    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    // Method to insert a new task
    public void insert(Task task) {
        executorService.execute(() -> taskDao.insert(task));
    }

    // Method to update an existing task
    public void update(Task task) {
        executorService.execute(() -> taskDao.update(task));
    }

    // Method to delete a task
    public void delete(Task task) {
        executorService.execute(() -> taskDao.delete(task));
    }

    public List<Task> getAllTasksSync() {
        try {
            return executorService.submit(taskDao::getAllTasksSync).get();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }





}