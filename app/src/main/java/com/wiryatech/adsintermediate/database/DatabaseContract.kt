package com.wiryatech.adsintermediate.database

import android.provider.BaseColumns

internal class DatabaseContract {

    internal class NoteColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "watchlist"
            const val _ID = "id"
            const val TITLE = "title"
            const val DESC = "desc"
            const val GENRE = "genre"
            const val POSTER = "poster"
            const val TRAILER = "trailer"
            const val RATING = "rating"
        }
    }
}