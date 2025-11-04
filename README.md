# MAD204-LAB01-JENNYFERPARMAR
The Lab 3 Notes App is a simple Android application built using Kotlin that allows users to quickly add, view, and delete personal notes. It demonstrates the use of RecyclerView, Adapters, and SharedPreferences for storing and displaying data in a user-friendly way.

Features
Add Notes: Users can write new notes through an input field and add them instantly to the list.
Delete Notes: Long-pressing a note removes it from the list.
Undo Delete: Accidentally deleted a note? You can undo it using the Snackbar action.
Auto-Save: Notes are automatically saved using SharedPreferences and persist even after the app is closed.
RecyclerView Display: All notes are shown dynamically in a scrollable list with smooth updates when items are added or removed.

Technical Details
Language: Kotlin
Framework: Android SDK
UI Components: EditText, Button, RecyclerView, Snackbar, LinearLayoutManager
Data Storage: SharedPreferences (with Gson for JSON serialization)
Architecture: Single-Activity App with modular Kotlin classes (Note, NotesAdapter, Storage)

Learning Outcomes

This project helps understand:
How to create and manage custom data models in Kotlin (data class Note)
How to bind dynamic lists with a RecyclerView and custom Adapter
How to handle user interactions like button clicks and long presses
How to persist data locally with SharedPreferences and Gson

