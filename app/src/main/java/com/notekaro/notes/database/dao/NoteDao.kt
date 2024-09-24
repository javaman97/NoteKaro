package com.notekaro.notes.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.notekaro.notes.model.Note

@Dao
interface NoteDao {
    @Insert(onConflict=OnConflictStrategy.IGNORE)
     suspend fun insert(note: Note)

    @Delete
     suspend fun delete(note: Note)

     @Update
     suspend fun update(note: Note)

    @Query("Select * from notes_table order by id ASC")
     fun getAllNotes():LiveData<List<Note>>
}