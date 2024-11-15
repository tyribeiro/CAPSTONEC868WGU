package com.example.taskmanagerapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanagerapp.R;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder> {

    private final List<String[]> reportData;

    public ReportAdapter(List<String[]> reportData) {
        this.reportData = reportData;
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_report, parent, false);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        String[] rowData = reportData.get(position);
        holder.textViewData.setText(formatRowData(rowData));
    }

    @Override
    public int getItemCount() {
        return reportData.size();
    }

    private String formatRowData(String[] rowData) {
        StringBuilder formattedRow = new StringBuilder();
        for (String data : rowData) {
            formattedRow.append(data).append("  |  ");
        }
        return formattedRow.toString();
    }

    public static class ReportViewHolder extends RecyclerView.ViewHolder {

        TextView textViewData;

        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewData = itemView.findViewById(R.id.textViewData);
        }
    }
}
