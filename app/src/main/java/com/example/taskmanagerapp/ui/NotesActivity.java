package com.example.taskmanagerapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanagerapp.R;
import com.example.taskmanagerapp.entities.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity {

    private NoteViewModel noteViewModel;
    private NoteAdapter noteAdapter;
    private RecyclerView recyclerViewNotes;
    private Button buttonAddNote;
    private int taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
        buttonAddNote = findViewById(R.id.buttonAddNote);

        // Getting taskId from the intent
        taskId = getIntent().getIntExtra("taskId", -1);

        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter with an empty list and a click listener for notes
        noteAdapter = new NoteAdapter(new ArrayList<>(), note -> {
            // Handle the note click
            Intent intent = new Intent(NotesActivity.this, EditNoteActivity.class);
            intent.putExtra("noteId", note.getId());
            startActivity(intent);
        });
        recyclerViewNotes.setAdapter(noteAdapter);

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        // Observing notes for the specific task
        noteViewModel.getNotesForTask(taskId).observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                noteAdapter.setNotes(notes);
            }
        });

        // Add Note button click listener
        buttonAddNote.setOnClickListener(v -> {
            // Start AddNoteActivity when button is clicked
            Intent intent = new Intent(NotesActivity.this, AddNoteActivity.class);
            intent.putExtra("taskId", taskId);
            startActivity(intent);
        });
    }
}
