package com.rayray.madlevel5task1.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rayray.madlevel5task1.dao.NoteDao
import com.rayray.madlevel5task1.helper.Converters
import com.rayray.madlevel5task1.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NotepadRoomDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        private const val DATABASE_NAME = "NOTE_DATABASE"

        private var notepadRoomDatabaseInstance: NotepadRoomDatabase? = null

        fun getDatabase(context: Context): NotepadRoomDatabase? {
            if (notepadRoomDatabaseInstance == null) {
                synchronized(NotepadRoomDatabase::class.java) {
                    notepadRoomDatabaseInstance = Room.databaseBuilder(
                        context.applicationContext, NotepadRoomDatabase::class.java,
                        DATABASE_NAME
                    ).fallbackToDestructiveMigration()
                        .addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                notepadRoomDatabaseInstance?.let { database ->
                                    CoroutineScope(Dispatchers.IO).launch {
                                        database.noteDao().insertNotepad(Note(0, "Titel", Date(), "Mijn notities"))
                                    }
                                }
                            }
                        })
                        .build()
                }
            }
            return notepadRoomDatabaseInstance
        }
    }

}