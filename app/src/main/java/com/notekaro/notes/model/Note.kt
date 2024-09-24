package com.notekaro.notes.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
class Note(@ColumnInfo(name = "note_table")val text:String) {
    @PrimaryKey(autoGenerate = true) var id =0

}