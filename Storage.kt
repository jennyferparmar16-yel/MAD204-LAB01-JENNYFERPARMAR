/**
  Course Code: CSD204 – Mobile Application Development
  Lab Number: Lab 3
  Student Name: Jennyfer Parmar
  Student ID: A00201240
  Date of Submission: 4th November 2025

  File Name: Storage.kt

  Description:
  The Storage class handles saving and loading notes using SharedPreferences.
  It converts the list of Note objects to JSON using Gson for persistence,
  and restores them when the app restarts. This allows notes to remain
  available even after the app is closed or reopened.
 */
package com.example.lab3notesapp
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object Storage {
    private const val PREFS = "notes_prefs"
    private const val KEY_NOTES = "notes_json"
    private val gson = Gson()
    private val listType = object : TypeToken<List<Note>>() {}.type

    fun load(context: Context): MutableList<Note> {
        val json = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .getString(KEY_NOTES, null)
        return if (json.isNullOrEmpty()) mutableListOf()
        else gson.fromJson<List<Note>>(json, listType).toMutableList()
    }
    fun save(context: Context, notes: List<Note>) {
        val json = gson.toJson(notes)
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_NOTES, json)
            .apply()
    }
}