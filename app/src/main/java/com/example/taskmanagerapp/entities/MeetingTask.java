package com.example.taskmanagerapp.entities;

import androidx.room.Entity;

import java.util.Date;

@Entity
public class MeetingTask extends Task {

    public MeetingTask(String title, String status, int categoryId, String categoryName, Date creationDate) {
        super(title, status, categoryId, categoryName, creationDate);
    }

}
