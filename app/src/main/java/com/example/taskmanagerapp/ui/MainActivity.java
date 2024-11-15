package com.example.taskmanagerapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanagerapp.R;
import com.example.taskmanagerapp.entities.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TaskViewModel taskViewModel;
    private TaskAdapter taskAdapter;
    private List<Task> allTasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewTasks);
        FloatingActionButton buttonAddTask = findViewById(R.id.buttonAddTask);
        Button buttonGenerateReport = findViewById(R.id.buttonGenerateReports);
        SearchView searchView = findViewById(R.id.searchView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter(new ArrayList<>());
        recyclerView.setAdapter(taskAdapter);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        // Load all tasks
        taskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                allTasks.clear();
                taskAdapter.setTasks(allTasks);
                allTasks.addAll(tasks);
            }
        });

        // Add new task button
        buttonAddTask.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivity(intent);
        });

        // Generate report button
        buttonGenerateReport.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ReportActivity.class);
            startActivity(intent);
        });

        // Set up SearchView
        searchView.setQueryHint("Search Tasks");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterTasks(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterTasks(newText);
                return false;
            }
        });
    }

    private void filterTasks(String query) {
        List<Task> filteredTasks = new ArrayList<>();
        if (query.isEmpty()) {
            filteredTasks.addAll(allTasks);
        } else {
            for (Task task : allTasks) {
                if (task.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        task.getCategoryName().toLowerCase().contains(query.toLowerCase())) {
                    filteredTasks.add(task);
                }
            }
        }
        taskAdapter.setTasks(filteredTasks);
    }
}
