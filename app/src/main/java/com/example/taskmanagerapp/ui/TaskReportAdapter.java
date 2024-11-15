package com.example.taskmanagerapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanagerapp.R;
import com.example.taskmanagerapp.entities.Task;

import java.util.List;

public class TaskReportAdapter extends RecyclerView.Adapter<TaskReportAdapter.TaskViewHolder> {

    private final List<Task> tasks;

    public TaskReportAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_report, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.textViewTitle.setText(task.getTitle());
        holder.textViewCategory.setText(task.getCategoryName());  // Now this will work correctly
        holder.textViewCreationDate.setText(task.getCreationDate().toString());
    }


    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewCategory, textViewType, textViewCreationDate;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewCategory = itemView.findViewById(R.id.textViewCategory);
            textViewType = itemView.findViewById(R.id.textViewType);
            textViewCreationDate = itemView.findViewById(R.id.textViewCreationDate);
        }
    }
}
