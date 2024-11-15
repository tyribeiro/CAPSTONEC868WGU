package com.example.taskmanagerapp.entities;

import androidx.room.Entity;

import java.util.Date;

@Entity
public class WorkTask extends Task {

    // Constructor that matches the updated Task constructor
    public WorkTask(String title, String status, int categoryId, String categoryName, Date creationDate) {
        super(title, status, categoryId, categoryName, creationDate);
    }


}
