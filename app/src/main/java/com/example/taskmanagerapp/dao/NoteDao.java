package com.example.taskmanagerapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.taskmanagerapp.entities.Note;

import java.util.List;
@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM notes WHERE id = :noteId LIMIT 1")
    LiveData<Note> getNoteById(int noteId);

    @Query("SELECT * FROM notes WHERE taskId = :taskId")
    LiveData<List<Note>> getNotesForTask(int taskId);
}
