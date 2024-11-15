package com.example.taskmanagerapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.taskmanagerapp.dao.CategoryDao;
import com.example.taskmanagerapp.dao.NoteDao;
import com.example.taskmanagerapp.dao.TaskDao;
import com.example.taskmanagerapp.entities.Category;
import com.example.taskmanagerapp.entities.Note;
import com.example.taskmanagerapp.entities.Task;
import com.example.taskmanagerapp.utils.DateConverter;

@Database(entities = {Task.class, Note.class, Category.class}, version = 5, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class TaskDatabase extends RoomDatabase {
    private static TaskDatabase instance;

    public abstract TaskDao taskDao();
    public abstract NoteDao noteDao();
    public abstract CategoryDao categoryDao();

    public static synchronized TaskDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            TaskDatabase.class, "task_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
