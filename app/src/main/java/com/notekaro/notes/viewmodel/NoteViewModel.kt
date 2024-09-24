package com.notekaro.notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.notekaro.notes.database.NoteDatabase
import com.notekaro.notes.model.Note
import com.notekaro.notes.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NoteRepository
    val allNotes:LiveData<List<Note>>
            init{
                val dao= NoteDatabase.getDatabase(application).getNoteDao()
                repository= NoteRepository(dao)
                allNotes= repository.allNotes

            }
    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
         }
    }

    fun insertNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(note)
        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.update(note)

        }
    }
}