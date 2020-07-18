package com.wiryatech.adsintermediate.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.wiryatech.adsintermediate.database.DatabaseContract.*

internal class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "db_ads_inter"
        private const val DATABASE_VERSION = 1

        private val SQL_CREATE_TABLE = "CREATE TABLE ${NoteColumns.TABLE_NAME}" +
                "(${NoteColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${NoteColumns.TITLE} TEXT NOT NULL," +
                "${NoteColumns.DESC} TEXT NOT NULL," +
                "${NoteColumns.GENRE} TEXT NOT NULL," +
                "${NoteColumns.POSTER} TEXT NOT NULL," +
                "${NoteColumns.TRAILER} TEXT NOT NULL," +
                "${NoteColumns.RATING} TEXT NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${NoteColumns.TABLE_NAME}")
        onCreate(db)
    }
}