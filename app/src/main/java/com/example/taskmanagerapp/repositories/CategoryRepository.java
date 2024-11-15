package com.example.taskmanagerapp.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.taskmanagerapp.dao.CategoryDao;
import com.example.taskmanagerapp.database.TaskDatabase;
import com.example.taskmanagerapp.entities.Category;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CategoryRepository {
    private final CategoryDao categoryDao;
    private final ExecutorService executorService;

    public CategoryRepository(Application application) {
        TaskDatabase database = TaskDatabase.getInstance(application);
        categoryDao = database.categoryDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    // Synchronous method to get all categories
    public List<Category> getAllCategoriesSync() {
        return categoryDao.getAllCategoriesSync();
    }

    // Asynchronous method to get all categories
    public LiveData<List<Category>> getAllCategories() {
        return categoryDao.getAllCategories();
    }

        // Asynchronous method to insert category
        public void insert (Category category){
            executorService.execute(() -> categoryDao.insert(category));
        }
}
