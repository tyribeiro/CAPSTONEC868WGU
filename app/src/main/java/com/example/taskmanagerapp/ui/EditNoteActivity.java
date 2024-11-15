package com.example.taskmanagerapp.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.taskmanagerapp.R;
import com.example.taskmanagerapp.entities.Note;

public class EditNoteActivity extends AppCompatActivity {

    private EditText editTextNoteTitle;
    private EditText editTextNoteContent;
    private Button buttonSaveNote;
    private Button buttonDeleteNote;
    private Note note;
    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        // Initialize views
        editTextNoteTitle = findViewById(R.id.editTextNoteTitle);
        editTextNoteContent = findViewById(R.id.editTextNoteContent);
        buttonSaveNote = findViewById(R.id.buttonSaveNote);
        buttonDeleteNote = findViewById(R.id.buttonDeleteNote);
        Button buttonBack = findViewById(R.id.buttonBack);

        // Back button functionality
        buttonBack.setOnClickListener(v -> finish());

        // Get NoteViewModel instance
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        // Load note from intent
        if (getIntent() != null && getIntent().hasExtra("noteId")) {
            int noteId = getIntent().getIntExtra("noteId", -1);
            noteViewModel.getNoteById(noteId).observe(this, loadedNote -> {
                if (loadedNote != null) {
                    note = loadedNote;
                    editTextNoteTitle.setText(note.getTitle());
                    editTextNoteContent.setText(note.getContent());
                }
            });
        }

        // Save note functionality
        buttonSaveNote.setOnClickListener(v -> {
            String noteTitle = editTextNoteTitle.getText().toString().trim();
            String noteContent = editTextNoteContent.getText().toString().trim();

            if (noteTitle.isEmpty()) {
                new AlertDialog.Builder(EditNoteActivity.this)
                        .setTitle("Invalid Input")
                        .setMessage("Note title cannot be empty. Please enter a valid title.")
                        .setPositiveButton("OK", null)
                        .show();
            } else {
                note.setTitle(noteTitle);
                note.setContent(noteContent);
                noteViewModel.update(note);
                finish();
            }
        });

        // Delete note functionality
        buttonDeleteNote.setOnClickListener(v -> {
            new AlertDialog.Builder(EditNoteActivity.this)
                    .setTitle("Delete Note")
                    .setMessage("Are you sure you want to delete this note?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        noteViewModel.delete(note);
                        finish();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }
}
