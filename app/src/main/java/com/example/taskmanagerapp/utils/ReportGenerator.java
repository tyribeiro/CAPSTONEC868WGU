package com.example.taskmanagerapp.utils;

import android.content.Context;
import android.widget.Toast;

import com.example.taskmanagerapp.dao.TaskDao;
import com.example.taskmanagerapp.entities.Category;
import com.example.taskmanagerapp.entities.Task;
import com.example.taskmanagerapp.ui.ReportViewerActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReportGenerator {

    private final Context context;
    private final TaskDao taskDao;
    private final ExecutorService executorService;

    public ReportGenerator(Context context, TaskDao taskDao) {
        this.context = context;
        this.taskDao = taskDao;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void generateTaskReport(List<Task> tasks) {
        executorService.execute(() -> {
            List<String[]> reportDataList = new ArrayList<>();
            reportDataList.add(new String[]{"Task ID", "Title", "Category", "Creation Date"}); // Headers

            for (Task task : tasks) {
                reportDataList.add(new String[]{
                        String.valueOf(task.getId()),
                        task.getTitle(),
                        task.getCategoryName(),
                        task.getCreationDate().toString()
                });
            }

            showReportGeneratedMessage("Task report generated successfully!", reportDataList);
        });
    }

    public void generateCategoryReport(List<Category> categories) {
        executorService.execute(() -> {
            List<String[]> reportDataList = new ArrayList<>();
            reportDataList.add(new String[]{"Category ID", "Category Name", "Number of Tasks"}); // Headers

            for (Category category : categories) {
                int numberOfTasks = taskDao.getTaskCountByCategory(category.getId());
                reportDataList.add(new String[]{
                        String.valueOf(category.getId()),
                        category.getName(),
                        String.valueOf(numberOfTasks)
                });
            }

            showReportGeneratedMessage("Category report generated successfully!", reportDataList);
        });
    }

    private void showReportGeneratedMessage(String message, List<String[]> reportDataList) {
        // To ensure we are running this on the main thread
        new android.os.Handler(android.os.Looper.getMainLooper()).post(() -> {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            // Optionally, open the ReportViewerActivity to show the report
            ReportViewerActivity.openReport(context, reportDataList);
        });
    }
}
