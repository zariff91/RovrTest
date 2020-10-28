package com.zariff.rovrtest.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GlobalLocalData::class], version = 1)
abstract class GlobalDatabase : RoomDatabase() {

    abstract fun globalDao(): GlobalDao

    companion object {
        @Volatile
        private var instance: GlobalDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK)
        {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        fun buildDatabase(context: Context) = Room.databaseBuilder(

                context.applicationContext,
                GlobalDatabase::class.java,
                "globaldatabase"

        )
                .allowMainThreadQueries()
                .build()

    }
}