package com.example.taskmanagerapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanagerapp.R;
import com.example.taskmanagerapp.dao.TaskDao;
import com.example.taskmanagerapp.entities.Category;

import java.util.List;

public class CategoryReportAdapter extends RecyclerView.Adapter<CategoryReportAdapter.CategoryViewHolder> {

    private final List<Category> categoryList;
    private final TaskDao taskDao;

    public CategoryReportAdapter(List<Category> categoryList, TaskDao taskDao) {
        this.categoryList = categoryList;
        this.taskDao = taskDao;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category_report, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.textViewCategoryName.setText(category.getName());

        // Get task count for the category
        int taskCount = taskDao.getTaskCountByCategory(category.getId());
        holder.textViewTaskCount.setText("Tasks: " + taskCount);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCategoryName;
        TextView textViewTaskCount;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCategoryName = itemView.findViewById(R.id.textViewCategoryName);
            textViewTaskCount = itemView.findViewById(R.id.textViewTaskCount);
        }
    }
}
