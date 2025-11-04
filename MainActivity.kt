/** Course Code: MAD204 – Mobile Application Development
    Lab Number: Lab 3
    Student Name: Jennyfer Parmar
    Student ID: A00201240
    Date of Submission: 4th November 2025

    File Name: activity_main.xml

    Description:
    This layout defines the main screen of the Notes App.
    It contains an EditText for note input, an "Add Note" button,
    and a RecyclerView to display all saved notes.*/
package com.example.lab3notesapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

// ✅ your own imports
import com.example.lab3notesapp.Note
import com.example.lab3notesapp.NotesAdapter
import com.example.lab3notesapp.Storage

class MainActivity : AppCompatActivity() {

    private lateinit var notes: MutableList<Note>
    private lateinit var adapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Load notes (from SharedPreferences if available)
        notes = Storage.load(this)

        // Get views
        val rvNotes = findViewById<RecyclerView>(R.id.rvNotes)
        val etNote = findViewById<EditText>(R.id.editTextText)
        val btnAdd = findViewById<Button>(R.id.addNoteButton)

        // Setup RecyclerView + Adapter
        adapter = NotesAdapter(notes) { pos ->
            val deleted = notes[pos]
            notes.removeAt(pos)
            adapter.notifyItemRemoved(pos)

            Snackbar.make(rvNotes, "Note deleted", Snackbar.LENGTH_LONG)
                .setAction("UNDO") {
                    notes.add(pos, deleted)
                    adapter.notifyItemInserted(pos)
                }
                .show()
        }
        rvNotes.layoutManager = LinearLayoutManager(this)
        rvNotes.adapter = adapter

        // Add note button
        btnAdd.setOnClickListener {
            val text = etNote.text.toString().trim()
            if (text.isEmpty()) {
                Toast.makeText(this, "Enter a note", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            notes.add(0, Note(text))
            adapter.notifyItemInserted(0)
            rvNotes.scrollToPosition(0)
            etNote.text.clear()
            Toast.makeText(this, "Note added", Toast.LENGTH_SHORT).show()
        }

        // For safe insets (default)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }
    }

    override fun onPause() {
        super.onPause()
        Storage.save(this, notes)
    }
}
