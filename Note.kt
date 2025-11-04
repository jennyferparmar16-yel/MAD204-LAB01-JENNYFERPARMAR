/*
Course Code: MAD204 – Mobile Application Development
 Lab Number: Lab 3
 Student Name: Jennyfer Parmar
    Student ID: A00201240
    Date of Submission: 4th November 2025

 File Name: Note.kt

 Description:
 The Note data class represents a single note item in the app.
 It stores the note text and the time it was created (in milliseconds).
 Each Note object is displayed in the RecyclerView through the NotesAdapter.
 */
package com.example.lab3notesapp



data class Note (
    val text: String,
    val createdAtMillis: Long = System.currentTimeMillis()
)