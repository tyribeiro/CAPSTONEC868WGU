package com.example.taskmanagerapp.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.taskmanagerapp.R;
import com.example.taskmanagerapp.entities.Category;
import com.example.taskmanagerapp.entities.Task;

import java.util.List;

public class EditTaskActivity extends AppCompatActivity {
    private EditText editTextTaskTitle;
    private Button buttonSaveTask;
    private Button buttonDeleteTask;
    private Button buttonBack;

    private Button buttonViewNotes;
    private Spinner spinnerCategory;
    private Task task;
    private TaskViewModel taskViewModel;
    private CategoryViewModel categoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        editTextTaskTitle = findViewById(R.id.editTextTaskTitle);
        buttonSaveTask = findViewById(R.id.buttonSaveTask);
        buttonDeleteTask = findViewById(R.id.buttonDeleteTask);
        buttonBack = findViewById(R.id.buttonBack);
        spinnerCategory = findViewById(R.id.spinnerTaskType);
        buttonViewNotes = findViewById(R.id.buttonViewNotes);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

        // Set up Spinner with categories
        categoryViewModel.getAllCategories().observe(this, categories -> {
            ArrayAdapter<Category> adapter = new ArrayAdapter<>(
                    EditTaskActivity.this,
                    android.R.layout.simple_spinner_item,
                    categories
            );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerCategory.setAdapter(adapter);
        });

        // Load Task from Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("task")) {
            task = (Task) intent.getSerializableExtra("task");
            editTextTaskTitle.setText(task.getTitle());

            // Set the correct category in the spinner
            categoryViewModel.getAllCategories().observe(this, categories -> {
                for (int i = 0; i < categories.size(); i++) {
                    if (categories.get(i).getId() == task.getCategoryId()) {
                        spinnerCategory.setSelection(i);
                        break;
                    }
                }
            });
        }

        buttonSaveTask.setOnClickListener(v -> {
            String taskTitle = editTextTaskTitle.getText().toString().trim();
            Category selectedCategory = (Category) spinnerCategory.getSelectedItem();

            if (taskTitle.isEmpty()) {
                new AlertDialog.Builder(EditTaskActivity.this)
                        .setTitle("Invalid Input")
                        .setMessage("Task title cannot be empty. Please enter a valid title.")
                        .setPositiveButton("OK", null)
                        .show();
            } else if (selectedCategory != null) {
                task.setTitle(taskTitle);
                task.setCategoryId(selectedCategory.getId());
                taskViewModel.update(task);
                finish();
            }
        });

        buttonDeleteTask.setOnClickListener(v -> {
            taskViewModel.delete(task);
            finish();
        });

        buttonBack.setOnClickListener(v -> finish());

        buttonViewNotes.setOnClickListener(v -> {
            if (task != null) {
                // Launch NotesActivity and pass the taskId
                Intent notesIntent = new Intent(EditTaskActivity.this, NotesActivity.class);
                notesIntent.putExtra("taskId", task.getId());
                startActivity(notesIntent);
            }
        });

        buttonBack.setOnClickListener(v -> finish());
}


}
