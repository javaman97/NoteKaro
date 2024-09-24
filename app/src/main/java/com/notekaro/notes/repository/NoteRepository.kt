package com.notekaro.notes.repository

import androidx.lifecycle.LiveData
import com.notekaro.notes.database.dao.NoteDao
import com.notekaro.notes.model.Note

class NoteRepository(private val noteDao: NoteDao){

    val allNotes:LiveData<List<Note>> = noteDao.getAllNotes()

     suspend fun insert(note: Note)
    {
        noteDao.insert(note)
    }
     suspend fun delete(note: Note)
    {
        noteDao.delete(note)
    }

    suspend fun update(note: Note){
        noteDao.update(note)
    }
}