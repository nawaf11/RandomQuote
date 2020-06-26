package com.nawaf.example.randomquote.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nawaf.example.randomquote.data.local.dao.QuoteDao
import com.nawaf.example.randomquote.data.Quote

@Database(version = 1, exportSchema = false, entities = [Quote::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao

    companion object {

        fun build(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app.db"
            ).build()
        }

    }

}