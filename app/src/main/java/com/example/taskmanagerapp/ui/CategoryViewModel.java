package com.example.taskmanagerapp.ui;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.taskmanagerapp.entities.Category;
import com.example.taskmanagerapp.repositories.CategoryRepository;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {
    private final CategoryRepository categoryRepository;

    public CategoryViewModel(Application application) {
        super(application);
        categoryRepository = new CategoryRepository(application);
    }

    public LiveData<List<Category>> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    // Add insert method to add categories
    public void insert(Category category) {
        categoryRepository.insert(category);
    }
    public List<Category> getAllCategoriesSync() {
        return categoryRepository.getAllCategoriesSync();
    }

}
