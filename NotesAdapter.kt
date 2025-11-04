/* Course Code: MAD204 – Mobile Application Development
 Lab Number: Lab 3
 Student Name: Jennyfer Parmar
    Student ID: A00201240
    Date of Submission: 4th November 2025

 File Name: NotesAdapter.kt

 Description:
 The NotesAdapter class connects the list of notes to the RecyclerView.
 It defines how each note item is displayed using a ViewHolder and handles
 user interactions such as long-pressing a note to delete it. It also provides
 helper methods to add, remove, and restore notes dynamically.
*/
package com.example.lab3notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(
private val items: MutableList<Note>,
private val onItemLongPress: (position: Int) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NoteVH>() {

    inner class NoteVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNote: TextView = itemView.findViewById(R.id.tvNote)

        fun bind(note: Note) {
            tvNote.text = note.text
            itemView.setOnLongClickListener {
                onItemLongPress(bindingAdapterPosition)
                true
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return NoteVH(view)
    }

    override fun onBindViewHolder(holder: NoteVH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun addNote(note: Note) {
        items.add(0, note)
        notifyItemInserted(0)
    }
    fun removeAt(pos: Int): Note {
        val removed = items.removeAt(pos)
        notifyItemRemoved(pos)
        return removed
    }

    fun restoreAt(pos: Int, note: Note) {
        items.add(pos, note)
        notifyItemInserted(pos)
    }

    fun getAllNotes(): List<Note> = items
}