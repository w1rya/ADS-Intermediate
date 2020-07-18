package com.wiryatech.adsintermediate.utils

import android.database.Cursor
import com.wiryatech.adsintermediate.database.DatabaseContract.*
import com.wiryatech.adsintermediate.model.MovieModel

object MappingHelper {

    fun mapCursorToArrayList(cursor: Cursor) : ArrayList<MovieModel> {
        val wishlist = ArrayList<MovieModel>()
        cursor.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(NoteColumns._ID))
                val title = getString(getColumnIndexOrThrow(NoteColumns.TITLE))
                val desc = getString(getColumnIndexOrThrow(NoteColumns.DESC))
                val genre = getString(getColumnIndexOrThrow(NoteColumns.GENRE))
                val poster = getInt(getColumnIndexOrThrow(NoteColumns.POSTER))
                val trailer = getInt(getColumnIndexOrThrow(NoteColumns.TRAILER))
                val rating = getFloat(getColumnIndexOrThrow(NoteColumns.RATING))
                wishlist.add(MovieModel(id, title, desc, genre, poster, trailer, rating))
            }
        }
        return wishlist
    }
}