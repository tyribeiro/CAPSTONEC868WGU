package com.example.taskmanagerapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanagerapp.R;

import java.util.ArrayList;
import java.util.List;

public class ReportViewerActivity extends AppCompatActivity {
    private static final String EXTRA_REPORT_DATA = "extra_report_data";
    private List<String[]> reportDataList;

    public static void openReport(Context context, List<String[]> reportDataList) {
        Intent intent = new Intent(context, ReportViewerActivity.class);
        intent.putExtra(EXTRA_REPORT_DATA, new ArrayList<>(reportDataList));
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_viewer);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewReport);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reportDataList = (List<String[]>) getIntent().getSerializableExtra(EXTRA_REPORT_DATA);
        if (reportDataList != null) {
            ReportAdapter reportAdapter = new ReportAdapter(reportDataList);
            recyclerView.setAdapter(reportAdapter);
        }
    }
}
