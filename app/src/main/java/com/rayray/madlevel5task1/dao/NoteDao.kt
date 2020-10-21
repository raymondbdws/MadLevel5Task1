package com.rayray.madlevel5task1.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.rayray.madlevel5task1.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table LIMIT 1")
    fun getNotepad(): LiveData<Note?>

    @Insert
    fun insertNotepad(note: Note)

    @Update
    fun updateNotepad(note: Note)
}
