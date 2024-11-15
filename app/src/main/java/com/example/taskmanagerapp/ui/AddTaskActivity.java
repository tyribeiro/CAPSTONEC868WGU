package com.example.taskmanagerapp.ui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.taskmanagerapp.R;
import com.example.taskmanagerapp.entities.Category;
import com.example.taskmanagerapp.entities.Task;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddTaskActivity extends AppCompatActivity {
    private EditText editTextTaskTitle;
    private Button buttonSaveTask;
    private Button buttonSetReminder;
    private Button buttonBack;
    private Spinner spinnerCategory;
    private TaskViewModel taskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editTextTaskTitle = findViewById(R.id.editTextTaskTitle);
        buttonSaveTask = findViewById(R.id.buttonSaveTask);
        buttonBack = findViewById(R.id.buttonBack);
        spinnerCategory = findViewById(R.id.spinnerCategory);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        // Set up the Spinner with categories
        taskViewModel.getAllCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                ArrayAdapter<Category> adapter = new ArrayAdapter<>(
                        AddTaskActivity.this,
                        android.R.layout.simple_spinner_item,
                        categories
                );
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCategory.setAdapter(adapter);
            }
        });

        buttonSaveTask.setOnClickListener(v -> {
            String taskTitle = editTextTaskTitle.getText().toString().trim();
            Category selectedCategory = (Category) spinnerCategory.getSelectedItem(); // Get selected category

            if (taskTitle.isEmpty()) {
                // Show an alert dialog
                new AlertDialog.Builder(AddTaskActivity.this)
                        .setTitle("Invalid Input")
                        .setMessage("Task title cannot be empty. Please enter a valid title.")
                        .setPositiveButton("OK", null)
                        .show();
            } else {
                Date creationDate = new Date(); // Set the creation date to now
                Task task = new Task(taskTitle, "New", selectedCategory.getId(), selectedCategory.getName(), creationDate);
                taskViewModel.insert(task);
                finish();
            }
        });


        buttonBack.setOnClickListener(v -> finish());
    }
}
