package com.example.taskmanagerapp.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.taskmanagerapp.entities.Category;
import com.example.taskmanagerapp.entities.Task;
import com.example.taskmanagerapp.repositories.CategoryRepository;
import com.example.taskmanagerapp.repositories.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository taskRepository;
    private CategoryRepository categoryRepository;
    private LiveData<List<Task>> allTasks;
    private LiveData<List<Category>> allCategories;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        taskRepository = new TaskRepository(application);
        categoryRepository = new CategoryRepository(application);
        allTasks = taskRepository.getAllTasks();
        allCategories = categoryRepository.getAllCategories();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public void insert(Task task) {
        taskRepository.insert(task);
    }

    public void update(Task task) {
        taskRepository.update(task);
    }

    public void delete(Task task) {
        taskRepository.delete(task);
    }

    // New method to get all categories
    public LiveData<List<Category>> getAllCategories() {
        return allCategories;
    }

    public List<Task> getAllTasksSync() {
        try {
            return taskRepository.getAllTasksSync();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
