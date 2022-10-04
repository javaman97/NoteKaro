package com.example.notes

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao){

    val allNotes:LiveData<List<Note>> = noteDao.getAllNotes()

     fun insert(note: Note)
    {
        noteDao.insert(note)
    }
     fun delete(note: Note)
    {
        noteDao.delete(note)
    }
}