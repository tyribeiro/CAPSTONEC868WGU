package com.example.taskmanagerapp.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.taskmanagerapp.entities.Note;
import com.example.taskmanagerapp.repositories.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private final NoteRepository noteRepository;
    private final LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAllNotes();
    }

    // Method to get all notes
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    // Method to get a specific note by its ID
    public LiveData<Note> getNoteById(int noteId) {
        return noteRepository.getNoteById(noteId);
    }

    // Method to insert a new note
    public void insert(Note note) {
        noteRepository.insert(note);
    }

    // Method to update an existing note
    public void update(Note note) {
        noteRepository.update(note);
    }

    // Method to delete a note
    public void delete(Note note) {
        noteRepository.delete(note);
    }

    public LiveData<List<Note>> getNotesForTask(int taskId) {
        return noteRepository.getNotesForTask(taskId);
    }

}
