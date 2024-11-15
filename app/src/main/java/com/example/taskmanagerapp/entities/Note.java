package com.example.taskmanagerapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "notes")
public class Note implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String content;
    private int taskId;  // Foreign key to associate with a specific task

    // Constructor
    public Note(String title, String content, int taskId) {
        this.title = title;
        this.content = content;
        this.taskId = taskId;
    }

    // Empty constructor
    public Note() {
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getTaskId() {
        return taskId;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
