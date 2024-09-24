package com.notekaro.notes.repository

import androidx.lifecycle.LiveData
import com.notekaro.notes.database.dao.NoteDao
import com.notekaro.notes.model.Note

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

    fun update(note: Note){
        noteDao.update(note)
    }
}