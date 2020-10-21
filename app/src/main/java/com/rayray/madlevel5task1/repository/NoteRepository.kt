package com.rayray.madlevel5task1.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.rayray.madlevel5task1.dao.NoteDao
import com.rayray.madlevel5task1.database.NotepadRoomDatabase
import com.rayray.madlevel5task1.model.Note

class NoteRepository(context: Context) {

    private val noteDao: NoteDao

    init {
        val database = NotepadRoomDatabase.getDatabase(context)
        noteDao = database!!.noteDao()
    }

    fun getNotePad(): LiveData<Note?> {
        return noteDao.getNotepad()
    }

    suspend fun updateNotepad(note: Note){
        noteDao.updateNotepad(note)
    }
}