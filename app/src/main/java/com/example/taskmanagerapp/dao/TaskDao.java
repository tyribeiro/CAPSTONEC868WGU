package com.example.taskmanagerapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.taskmanagerapp.entities.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("SELECT * FROM task WHERE id = :id")
    LiveData<Task> getTaskById(int id);

    @Query("SELECT * FROM task")
    LiveData<List<Task>> getAllTasks();

    @Query("SELECT * FROM task")
    List<Task> getAllTasksSync();

    @Query("SELECT * FROM task WHERE categoryId = :categoryId")
    LiveData<List<Task>> getTasksByCategory(int categoryId);

    @Query("SELECT COUNT(*) FROM task WHERE categoryId = :categoryId")
    int getTaskCountByCategory(int categoryId);

    @Query("SELECT * FROM task WHERE id = :taskId")
    Task getTaskByIdSync(int taskId);
}
