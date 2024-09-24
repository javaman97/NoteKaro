package com.notekaro.notes.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.notekaro.notes.model.Note

@Dao
interface NoteDao {
    @Insert(onConflict=OnConflictStrategy.IGNORE)
     fun insert(note: Note)

    @Delete
     fun delete(note: Note)

     @Update
     fun update(note: Note)

    @Query("Select * from notes_table order by id ASC")
    fun getAllNotes():LiveData<List<Note>>
}