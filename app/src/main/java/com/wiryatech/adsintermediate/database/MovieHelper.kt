package com.wiryatech.adsintermediate.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.wiryatech.adsintermediate.database.DatabaseContract.*

class MovieHelper(context: Context) {

    companion object {
        private const val DATABASE_TABLE = NoteColumns.TABLE_NAME
        private lateinit var dbHelper: DatabaseHelper
        private var INSTANCE: MovieHelper? = null
        private lateinit var db: SQLiteDatabase

        fun getInstance(context: Context): MovieHelper {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: MovieHelper(context)
            }
        }
    }

    init {
        dbHelper = DatabaseHelper(context)
    }

    @Throws(SQLException::class)
    fun open() {
        db = dbHelper.writableDatabase
    }
    fun close() {
        dbHelper.close()
        if (db.isOpen) db.close()
    }

    fun queryAll(): Cursor {
        return db.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "${NoteColumns._ID} ASC"
        )
    }

    fun queryById(id: String): Cursor {
        return db.query(
            DATABASE_TABLE,
            null,
            "${NoteColumns._ID} = ?",
            arrayOf(id),
            null,
            null,
            null
        )
    }

    fun insert(values: ContentValues?): Long = db.insert(DATABASE_TABLE, null, values)

    fun update(id: String, values: ContentValues?): Int = db.update(DATABASE_TABLE, values, "${NoteColumns._ID} = ?", arrayOf(id))

    fun deleteById(id: String): Int = db.delete(DATABASE_TABLE, "${NoteColumns._ID} = '$id'", null)

}