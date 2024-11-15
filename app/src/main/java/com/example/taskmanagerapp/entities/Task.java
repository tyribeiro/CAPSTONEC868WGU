package com.example.taskmanagerapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Task implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;  // Primary key

    private String title;
    private String status;
    private int categoryId;
    private String categoryName;  // New field for the category name
    private Date creationDate;

    // Updated constructor to include categoryName
    public Task(String title, String status, int categoryId, String categoryName, Date creationDate) {
        this.title = title;
        this.status = status;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.creationDate = creationDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
