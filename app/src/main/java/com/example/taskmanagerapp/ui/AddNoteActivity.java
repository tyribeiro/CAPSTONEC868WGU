package com.example.taskmanagerapp.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.taskmanagerapp.R;
import com.example.taskmanagerapp.entities.Note;

public class AddNoteActivity extends AppCompatActivity {
    private EditText editTextNoteTitle;
    private EditText editTextNoteContent;
    private Button buttonSaveNote;
    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        editTextNoteTitle = findViewById(R.id.editTextNoteTitle);
        editTextNoteContent = findViewById(R.id.editTextNoteContent);
        buttonSaveNote = findViewById(R.id.buttonSaveNote);

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        int taskId = getIntent().getIntExtra("taskId", -1);

        buttonSaveNote.setOnClickListener(v -> {
            String title = editTextNoteTitle.getText().toString().trim();
            String content = editTextNoteContent.getText().toString().trim();

            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(AddNoteActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            } else {
                Note note = new Note(title, content, taskId);
                noteViewModel.insert(note);
                finish();
            }
        });
    }
}
