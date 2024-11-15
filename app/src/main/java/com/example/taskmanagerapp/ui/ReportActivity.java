package com.example.taskmanagerapp.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.taskmanagerapp.R;
import com.example.taskmanagerapp.database.TaskDatabase;
import com.example.taskmanagerapp.entities.Category;
import com.example.taskmanagerapp.entities.Task;
import com.example.taskmanagerapp.utils.ReportGenerator;

import java.util.List;

public class ReportActivity extends AppCompatActivity {

    private CategoryViewModel categoryViewModel;
    private TaskViewModel taskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        // Get TaskDao from TaskDatabase
        TaskDatabase taskDatabase = TaskDatabase.getInstance(this);
        ReportGenerator reportGenerator = new ReportGenerator(this, taskDatabase.taskDao());

        findViewById(R.id.buttonTaskReport).setOnClickListener(v -> {
            taskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
                @Override
                public void onChanged(List<Task> tasks) {
                    if (tasks != null && !tasks.isEmpty()) {
                        reportGenerator.generateTaskReport(tasks);
                    }
                }
            });
        });

        findViewById(R.id.buttonCategoryReport).setOnClickListener(v -> {
            categoryViewModel.getAllCategories().observe(this, new Observer<List<Category>>() {
                @Override
                public void onChanged(List<Category> categories) {
                    if (categories != null && !categories.isEmpty()) {
                        reportGenerator.generateCategoryReport(categories);
                    }
                }
            });
        });
    }
}
