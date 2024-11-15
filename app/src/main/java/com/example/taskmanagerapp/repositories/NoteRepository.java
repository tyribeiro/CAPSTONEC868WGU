package com.example.taskmanagerapp.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.taskmanagerapp.dao.NoteDao;
import com.example.taskmanagerapp.database.TaskDatabase;
import com.example.taskmanagerapp.entities.Note;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepository {

    private final NoteDao noteDao;
    private final LiveData<List<Note>> allNotes;
    private final ExecutorService executorService;

    public NoteRepository(Application application) {
        TaskDatabase database = TaskDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
        executorService = Executors.newFixedThreadPool(2);
    }

    // Method to get all notes
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    // Method to get a specific note by its ID
    public LiveData<Note> getNoteById(int noteId) {
        return noteDao.getNoteById(noteId);
    }

    // Method to insert a new note
    public void insert(Note note) {
        executorService.execute(() -> noteDao.insert(note));
    }

    // Method to update an existing note
    public void update(Note note) {
        executorService.execute(() -> noteDao.update(note));
    }

    // Method to delete a note
    public void delete(Note note) {
        executorService.execute(() -> noteDao.delete(note));
    }

    public LiveData<List<Note>> getNotesForTask(int taskId) {
        return noteDao.getNotesForTask(taskId);
    }

}
